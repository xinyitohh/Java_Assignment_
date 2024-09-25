package admin;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.function.Function;

public class FileOperations {

    // Method to write an ArrayList of Scheduler objects to a file
    public static void writeToFile(ArrayList<User> userList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : userList) {
                String line = user.getUserId() + ";" +
                              user.getPassword() + ";" +
                              user.getName() + ";" +
                              user.getGender() + ";" +
                              user.getEmail() + ";" +
                              user.getPhone() + ";" +
                              user.getPicture() + ";" +
                              user.getStatus() + ";" +
                              user.getBalance(); 
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to read from a file and populate an ArrayList of Scheduler objects
    public static ArrayList<User> readFromFile(String fileName) {
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 9) { 
                    String id = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    String gender = parts[3];
                    String email = parts[4];
                    String phone = parts[5];
                    String picture = parts[6];
                    String status = parts[7];
                    double balance = Double.parseDouble(parts[8]);

                    User tempUser = null;
                    if (fileName.contains("scheduler")) {
                        tempUser = new Scheduler(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("customer")) {
                        tempUser = new Customer(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("manager")) {
                        tempUser = new Manager(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("admin")) {
                        tempUser = new Admin(id, password, name, gender, email, phone, picture, status, balance);
                    }

                    if (tempUser != null) {
                        userList.add(tempUser);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
      // Method to read from a file and populate an ArrayList of User objects
     public static void loadUsersFromFile(String fileName, ArrayList<User> allUsers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 9) {
                    String id = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    String gender = parts[3];
                    String email = parts[4];
                    String phone = parts[5];
                    String picture = parts[6];
                    String status = parts[7];
                    double balance = Double.parseDouble(parts[8]);

                    // Assuming each file is related to a specific role, we can determine the role by file name
                    User tempUser = null;
                    if (fileName.contains("scheduler")) {
                        tempUser = new Scheduler(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("customer")) {
                        tempUser = new Customer(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("manager")) {
                        tempUser = new Manager(id, password, name, gender, email, phone, picture, status, balance);
                    } else if (fileName.contains("admin")) {
                        tempUser = new Admin(id, password, name, gender, email, phone, picture, status, balance);
                    }

                    if (tempUser != null) {
                        allUsers.add(tempUser);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static <T> void writeFile(String fname, List<T> data, Function<T, String> mapper) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
        
        for (T item : data) {
            writer.write(mapper.apply(item)); // Apply the mapping function to convert object to string
            writer.newLine(); // Write a newline after each item
        }
        
        writer.close();
    }
    

    
    public static <T> ArrayList<T> readFile(String fname, Function<String[], T> mapper) throws IOException {
        ArrayList<T> store = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] part = line.split(";");
            T obj = mapper.apply(part); // Apply the mapping function
            if (obj != null) {
                store.add(obj); // Only add non-null objects to the list
            }
        }
        reader.close();
        return store;
    }
     
     
    // Method to load all users from multiple files
    public static ArrayList<User> loadAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        // Load users from each file
        loadUsersFromFile("scheduler.txt", allUsers);
        loadUsersFromFile("customer.txt", allUsers);
        loadUsersFromFile("manager.txt", allUsers);
        loadUsersFromFile("admin.txt", allUsers);

        return allUsers;
    }

}
