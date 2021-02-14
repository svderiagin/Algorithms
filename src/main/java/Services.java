import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Services {

    /**
     * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     */
    public static String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    /**
     * Convert CSV file to List of objects
     */
    public static <T> List serializeToObjectsList(File file, Class<T> tClass) {
        Charset windows_1252 = Charset.forName("windows-1252");
        try {
            return new CsvToBeanBuilder(new FileReader(file, windows_1252))
                    .withType(tClass)
                    .build()
                    .parse();

        } catch (IOException e) {
            throw new AssertionError("Error while convert csv file to objects list. StackTrace: " + e.getStackTrace());
        }
    }
}
