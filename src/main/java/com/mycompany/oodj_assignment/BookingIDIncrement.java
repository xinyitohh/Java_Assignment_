package com.mycompany.oodj_assignment;
import java.io.*;

public class BookingIDIncrement {
    private static final String BOOKING_ID_FILE = "booking_id.txt";

    public static String getNextBookingID() {
        int currentID = 1000;

        // Read the last saved booking ID from booking_id.txt
        File idFile = new File(BOOKING_ID_FILE);
        if (idFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(idFile))) {
                currentID = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading booking_id.txt: " + e.getMessage());
            }
        }

        currentID++;
        
        // Write the new ID back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_ID_FILE))) {
            writer.write(Integer.toString(currentID));
        } catch (IOException e) {
            System.out.println("Error writing to booking_id.txt: " + e.getMessage());
        }

        return "B" + currentID;
    }
}
