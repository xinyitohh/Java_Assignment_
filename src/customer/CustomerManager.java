package customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CustomerManager {
    public void updateCustomerFile(Customer updatedCustomer) {
        String filePath = "customer.txt";
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(updatedCustomer.getUserid() + ";")) {
                    // Replace the old line with the updated customer data
                    lines.add(updatedCustomer.toString());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        FileManager.writeToFile(filePath, lines.toArray(new String[0]), false);
    }
}
