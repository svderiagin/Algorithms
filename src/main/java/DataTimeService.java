import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DataTimeService {

    public static String current(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String current(String format, String timeZone) {
        return ZonedDateTime.now(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern(format));
    }

    public static String plusDays(int days, String format) {
        return LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

    public static String plusDays(int days, String format, String timeZone) {
        return LocalDateTime.now(ZoneId.of(timeZone)).plusDays(days).format(DateTimeFormatter.ofPattern(format));
    }

}
