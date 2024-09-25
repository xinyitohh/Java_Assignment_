package com.mycompany.oodj_assignment;

import java.io.*;

public class UserIDIncrement {
    
    private static final String CUSTOMER_ID_FILE = "customer_id.txt";
    private static final String CUSTOMER_FILE = "customer.txt";

    public static String getNextCustomerID() {
        int currentID = 1000;  // Default starting ID if the file doesn't exist

        // Read the last saved customer ID from customer_id.txt
        File idFile = new File(CUSTOMER_ID_FILE);
        if (idFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(idFile))) {
                currentID = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading customer_id.txt: " + e.getMessage());
            }
        }

        // Increment the ID
        currentID++;
        
                // Write the new ID back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_ID_FILE))) {
            writer.write(Integer.toString(currentID));
        } catch (IOException e) {
            System.out.println("Error writing to customer_id.txt: " + e.getMessage());
        }

        return "C" + currentID;  // Prefix the ID with "C" for customer IDs
    }

    public static void main(String[] args) {
        // Testing
        System.out.println(getNextCustomerID());
    }
}
