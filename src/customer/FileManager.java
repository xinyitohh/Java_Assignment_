package customer;
import java.io.*;
import admin.User;
import admin.Customer;
import admin.Frame_Login;

public class FileManager {
    // write data to any text file
    public static void writeToFile(String filePath, String data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(data);
            writer.newLine(); // Add a new line after writing the data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // handle multiple lines of data
    public static void writeToFile(String filePath, String[] data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine(); // Add a new line after each data line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
