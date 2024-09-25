package admin;

import java.util.ArrayList;

public class Customer extends User {
    
    public Customer(String userId, String password, String name, String gender, String email, String phone, String picture, String status) {
        super(userId, password, name, gender, email, phone, picture, status);
    }
    
    public Customer(String userId, String password, String name, String gender, String email, String phone, String picture, String status, double balance) {
        super(userId, password, name, gender, email, phone, picture, status, balance);
    }
    
    public Customer(String userId, String password) { //login
        super(userId, password);
    }
    
    public Customer(String userId, String password, String name, String gender, String email, String phone, String picture) {
        super(userId, password, name, gender, email, phone, picture);
    }
    
    @Override
    public String toString() {
        return this.getUserId() + ";" + this.getPassword() + ";" + this.getName() + ";" + this.getGender() + ";" + this.getEmail() + ";" + this.getPhone() + ";" + this.getPicture() + ";" + this.getStatus() + ";" + this.getBalance();
    }
    
    
    @Override
    public User login(){
        String fileName = "customer.txt";
        ArrayList<User> userList = FileOperations.readFromFile(fileName);

        for (User u : userList) {
            if (u.getUserId().equals(getUserId())) {
                if (u.getPassword().equals(getPassword())) {
                    // Login successful
                    System.out.println("Customer logged in: " + getUserId());
                    return u;  // Return user object
                } else {
                    System.out.println("Invalid password for Customer: " + getUserId());
                    return null;
                }
            }
        }

        System.out.println("Customer not found: " + getUserId());
        return null;}
    
    @Override
    public boolean verifyEmail(String email) {
        String fileName = "customer.txt";
        ArrayList<User> userList = FileOperations.readFromFile(fileName);

        for (User u : userList) {
            if (u.getUserId().equals(this.getUserId()) && u.getEmail().equals(email)) {
                System.out.println("Email verified: " + u.getEmail());
                return true;
            }
        }
        System.out.println("Email not found or doesn't match for: " + this.getUserId());
        return false;
    }


}
