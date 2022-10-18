package com.example.barberapp.Model;

public class User {

    private String name;
    private String email;
    private String password;
    private String phoneNo;
    private int userType = 0;

    public User(){

    }

    public User(String name, String email,String phoneNo,int userType) {
        this.name = name;
        this.email = email;

        this.phoneNo = phoneNo;
        this.userType = userType;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
