package admin;

import java.util.ArrayList;

public class Admin extends User {
    
    public Admin(String userId, String password, String name, String gender, String email, String phone, String picture, String status, double balance) {
        super(userId, password, name, gender, email, phone, picture, status, balance);
    }
    
    public Admin(String userId, String password, String name, String gender, String email, String phone, String picture, String status) {
        super(userId, password, name, gender, email, phone, picture, status);
    }
    
    public Admin(String userId, String password) { //login
        super(userId, password);
    }
    
    public Admin(String userId, String password, String name, String gender, String email, String phone, String picture) {
        super(userId, password, name, gender, email, phone, picture);
    }
    
    @Override
    public User login(){
        String fileName = "admin.txt";
        ArrayList<User> userList = FileOperations.readFromFile(fileName);

        for (User u : userList) {
            if (u.getUserId().equals(getUserId())) {
                if (u.getPassword().equals(getPassword())) {
                    // Login successful
                    System.out.println("Admin logged in: " + getUserId());
                    return u;  // Return user object
                } else {
                    System.out.println("Invalid password for Admin: " + getUserId());
                    return null;
                }
            }
        }

        System.out.println("Admin not found: " + getUserId());
        return null;
    }
    
    @Override
    public boolean verifyEmail(String email) {
        String fileName = "admin.txt";
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
    
    public void addScheduler(String id, String password, String name, String gender, String email, String phone, String picturePath) {
        Scheduler scheduler = new Scheduler(id, password, name, gender, email, phone, picturePath);
        ArrayList<User> schedulerList = FileOperations.readFromFile("scheduler.txt");
        schedulerList.add(scheduler);
        FileOperations.writeToFile(schedulerList, "scheduler.txt");
        System.out.println("Scheduler added: " + scheduler.getUserId());
        UserIDIncrement.getNextSchedulerID();
    }
    
    public void updateScheduler(String userId, String password, String name, String gender, String email, String phone) {
        ArrayList<User> schedulerList = FileOperations.readFromFile("scheduler.txt");
        for (User user : schedulerList) {
            if (user.getUserId().equals(userId)) {
                user.setPassword(password);
                user.setName(name);
                user.setGender(gender);
                user.setEmail(email);
                user.setPhone(phone);
                break;
            }
        }
        FileOperations.writeToFile(schedulerList, "scheduler.txt");
    }
    
    public void deleteScheduler(String userId) {
        ArrayList<User> schedulerList = FileOperations.readFromFile("scheduler.txt");
        schedulerList.removeIf(user -> user.getUserId().equals(userId));
        FileOperations.writeToFile(schedulerList, "scheduler.txt");
    }

    
    @Override
    public String toString() {
        return "Admin{" + super.toString() + "}\n";
    }
}
