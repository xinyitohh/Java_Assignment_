
package com.mycompany.oodj_assignment;


public class User {
    private String userid;
    private String password;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String picture;
    //private String stauts;
    private static User loggedInUser;
    private double balance;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }
    
    
    
    public User(String userid, String password, String name, String gender, String email, String phone, String picture, double balance) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.picture = picture;
        this.password = password;
        this.balance = balance;
    }

    public void setId(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserid() {
        return userid;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getPicture() {
        return picture;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    
}

 
