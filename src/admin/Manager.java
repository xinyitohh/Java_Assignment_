package admin;

import java.util.ArrayList;

public class Manager extends User {
    
    public Manager(String userId, String password, String name, String gender, String email, String phone, String picture, String status) {
        super(userId, password, name, gender, email, phone, picture, status);
    }
    
    public Manager(String userId, String password) { //login
        super(userId, password);
    }
    
    public Manager(String userId, String password, String name, String gender, String email, String phone, String picture) {
        super(userId, password, name, gender, email, phone, picture);
    }
    
    @Override
    public User login(){
        String fileName = "manager.txt";
        ArrayList<User> userList = FileOperations.readFromFile(fileName);

        for (User u : userList) {
            if (u.getUserId().equals(getUserId()) ) {
                
                if (u.getPassword().equals(getPassword())) {
                    // Login successful
                    System.out.println("Manager logged in: " + getUserId());
                    return u;  // Return user object
                    
                } else {
                    System.out.println("Invalid password for Manager: " + getUserId());
                    return null;
                }
                
            }
        }

        System.out.println("Manager not found: " + getUserId());
        return null;
    }
    
    @Override
    public boolean verifyEmail(String email) {
        String fileName = "manager.txt";
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

    @Override
    public String toString() {
        return "Manager{" + super.toString() + "}\n";
    }
}
