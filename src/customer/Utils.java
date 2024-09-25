package customer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
