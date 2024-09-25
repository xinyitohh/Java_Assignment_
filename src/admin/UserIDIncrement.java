package admin;
import java.io.*;


public class UserIDIncrement {

    private static final String CUSTOMER_ID_FILE = "customer_id.txt";
    private static final String SCHEDULER_ID_FILE = "scheduler_id.txt";

    public static String getNextCustomerID() {
        return "C" + getNextID(CUSTOMER_ID_FILE);
    }

    public static String getNextSchedulerID() {
        return "S" + getNextID(SCHEDULER_ID_FILE);
    }
    
    public static String getCurrentCustomerID() {
        return "C" + getCurrentID(CUSTOMER_ID_FILE);
    }

    public static String getCurrentSchedulerID() {
        return "S" + getCurrentID(SCHEDULER_ID_FILE);
    }


    private static int getNextID(String idFile) {
        int lastID = 1000; // Default start ID

        // Read the last ID from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(idFile))) {
            String line = reader.readLine();
            if (line != null) {
                lastID = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Increment ID
        int nextID = lastID + 1;

        // ID + 1 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idFile))) {
            writer.write(Integer.toString(nextID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextID;
    }
    
    private static int getCurrentID(String idFile) {
        int lastID = 1000; 

        try (BufferedReader reader = new BufferedReader(new FileReader(idFile))) {
            String line = reader.readLine();
            if (line != null) {
                lastID = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastID;
    }

    public static void main(String[] args) {
       
    }
}
