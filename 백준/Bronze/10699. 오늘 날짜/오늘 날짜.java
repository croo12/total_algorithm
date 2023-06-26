import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String answer = seoulTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        System.out.println(answer);
    }
}