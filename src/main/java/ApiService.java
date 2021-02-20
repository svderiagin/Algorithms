import com.google.gson.Gson;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import static java.lang.System.currentTimeMillis;

public class ApiService {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final MediaType VND_MS_EXCEL = MediaType.get("application/vnd.ms-excel");
    public static final MediaType X_WWW_FORM_URLENCODED = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");
    public static final MediaType RAW = MediaType.get("text/x-markdown; charset=utf-8");
    public static String tokenType = "Bearer "; // Space need for concatenate token type and token
    public static Gson gson = new Gson();
    public static OkHttpClient client = new OkHttpClient();

    public static JSONObject getJson(String url, String token, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        params.forEach((k, v) -> urlBuilder.addQueryParameter(k, v));
        url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", tokenType + token)
                .get()
                .build();

        Allure.addAttachment("Request", request.toString());

        try {
            Response response = client.newCall(request).execute();
            String responseStr = Objects.requireNonNull(response.body(), "Response must not be null").string();
            Allure.addAttachment("Response", responseStr);
            return new JSONObject(responseStr);
        } catch (IOException e) {
            throw new AssertionError("IOException: \n" + Arrays.toString(e.getStackTrace()));
        } catch (JSONException e) {
            throw new AssertionError("Response body text must begin with '{'");
        }
    }

    public static String getFile(String url, String token, Map<String, String> params, String filename) {
        long timeout = currentTimeMillis() + 30000L;
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        params.forEach((k, v) -> urlBuilder.addQueryParameter(k, v));
        url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", tokenType + token)
                .get()
                .build();

        Response response;
        do {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(Duration.ofSeconds(10)) // Need to download big files like timeslot type
                    .build();
            Allure.addAttachment("Request", request.toString());
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                throw new AssertionError("Error while executing request: \n" + Arrays.toString(e.getStackTrace()));
            }
            sleep(3000);
        } while (!response.isSuccessful() && currentTimeMillis() < timeout);

        if (!response.isSuccessful()) {
            try {
                String responseStr = Objects.requireNonNull(response.body(), "Response must not be null").string();
                Allure.addAttachment("Response", responseStr);
                throw new AssertionError("Response failed. Code: " + response.code() + ". Body: " + responseStr);
            } catch (IOException e) {
                throw new AssertionError("Error while getting body as string from response: \n" + Arrays.toString(e.getStackTrace()));
            }
        }

        // Second try/catch blocks need to understand where exactly exception happened: 1. connect to remote host 2. read response source
        try {
            BufferedSink sink = Okio.buffer(Okio.sink(new File(filename)));
            sink.writeAll(response.body().source());
            sink.close();
            return filename;
        } catch (IOException e) {
            throw new AssertionError("Error while writing to file: \n" + Arrays.toString(e.getStackTrace()));
        }

    }

    private static JSONObject _post(Request request) {
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseJson = new JSONObject();
            responseJson.put("success", response.isSuccessful());
            responseJson.put("code", response.code());
            responseJson.put("body", new JSONObject(response.body().string()));
            Allure.addAttachment("Response success", String.valueOf(responseJson.get("success")));
            Allure.addAttachment("Response code", String.valueOf(responseJson.get("code")));
            Allure.addAttachment("Response body", String.valueOf(responseJson.get("body")));
            return responseJson;
        } catch (IOException e) {
            throw new AssertionError("IOException: \n" + Arrays.toString(e.getStackTrace()));
        } catch (JSONException e) {
            throw new AssertionError("Response body text must begin with '{'");
        }
    }

    public static JSONObject post(String url, String token, String body) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", tokenType + token)
                .post(RequestBody.create(body, JSON))
                .build();
        Allure.addAttachment("Request", String.valueOf(request));
        Allure.addAttachment("Request body", body);
        return _post(request);
    }

    public static JSONObject post(String url, String token, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", tokenType + token)
                .post(body)
                .build();
        Allure.addAttachment("Request", String.valueOf(request));
        Allure.addAttachment("Request body", body.toString());
        return _post(request);
    }

    public static JSONObject delete(String url, String token) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer" + token)
                .delete()
                .build();
        Allure.addAttachment("Request", String.valueOf(request));
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", response.isSuccessful());
            jsonObject.put("code", response.code());
            jsonObject.put("body", new JSONObject(response.body().toString()));
            Allure.addAttachment("Response success", String.valueOf(jsonObject.get("success")));
            Allure.addAttachment("Response code", String.valueOf(jsonObject.get("code")));
            Allure.addAttachment("Response body", String.valueOf(jsonObject.get("body")));
            return jsonObject;
        } catch (IOException e) {
            throw new AssertionError("IOException: \n" + Arrays.toString(e.getStackTrace()));
        } catch (JSONException e) {
            throw new AssertionError("Response body text must begin with '{'");
        }
    }

    public static JSONObject uploadFile(String url, String token, File file, String type) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",
                        file.getName(),
                        RequestBody.create(new File(file.getAbsolutePath()),
                                VND_MS_EXCEL)
                )
                .addFormDataPart("select", type)
                .addFormDataPart("type", type)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer" + token)
                .post(body)
                .build();

        Allure.addAttachment("Request", String.valueOf(request));
        Allure.addAttachment("Body", body.toString());

        try {
            Response response = client.newCall(request).execute();
            String responseStr = Objects.requireNonNull(response.body(), "Response must not be null").string();
            Allure.addAttachment("Response", responseStr);
            return new JSONObject(responseStr);
        } catch (IOException e) {
            throw new AssertionError("IOException: \n" + Arrays.toString(e.getStackTrace()));
        } catch (JSONException e) {
            throw new AssertionError("Response body text must begin with '{'");
        }
    }


}
