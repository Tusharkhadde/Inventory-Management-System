import com.inventory.util.DatabaseConnection;
import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("Testing Database Connection...");
        long start = System.currentTimeMillis();
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                 System.out.println("Connection successful.");
                 conn.close();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("Caused by: " + cause.getMessage());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + "ms");
    }
}
