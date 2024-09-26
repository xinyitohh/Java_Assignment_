package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public abstract class User {
    private String userId;
    private String password;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String picture;
    private String status = "active";
    private double balance = 0;
    private static User loggedInUser;

    abstract User login();
    abstract boolean verifyEmail(String email);
    public void logout() {
        User.setLoggedInUser(null);  
        try{UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());}catch(Exception e){e.printStackTrace();}
        System.out.println("User logged out successfully.");
    }
    
    
    
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }
    
//    public static void setProfile(JLabel id, JLabel picture, User user){
//        id.setText(user.getUserId());
//        picture.setText(user.getPicture());
//
//        ImageIcon originalIcon = new ImageIcon(user.getPicture());
//        Image scaledImage = originalIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//
//        picture.setIcon(scaledIcon);
//        picture.setMaximumSize(new java.awt.Dimension(75, 75));
//        picture.setPreferredSize(new java.awt.Dimension(75, 75));
//    }
    
    public static void setProfile(JLabel picture, User user) {
        if (user == null) {
            System.out.println("User passed to setProfile is null!");
            return; 
        }

        ImageIcon originalIcon = new ImageIcon(user.getPicture());
        Image scaledImage = originalIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        picture.setIcon(scaledIcon);

        picture.setText(user.getUserId());
        picture.setForeground(Color.WHITE); 
        picture.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        picture.setHorizontalTextPosition(SwingConstants.CENTER); 
        picture.setVerticalTextPosition(SwingConstants.BOTTOM); 

        picture.setBackground(new Color(0, 0, 0, 0)); 
        picture.setOpaque(false);
        picture.setHorizontalAlignment(SwingConstants.CENTER); 
        picture.setVerticalAlignment(SwingConstants.CENTER);
        picture.setPreferredSize(new Dimension(75, 75)); 
    }
    
    public User(String userId, String password, String name, String gender, String email, String phone, String picture, String status, double balance) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.picture = picture;
        this.status = status;
        this.balance = balance;
    }

    public User(String userId, String password, String name, String gender, String email, String phone, String picture, String status) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.picture = picture;
        this.status = status;
    }
    
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    
    public User(String userId, String password, String name, String gender, String email, String phone, String picture) {
        this(userId, password, name, gender, email, phone, picture, "active");
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format("User[ID=%s, Name=%s, Gender=%s, Email=%s, Phone=%s, Picture=%s, Status=%s\n]",
                userId, name, gender, email, phone, picture, status);
    }
    
    
    
}
