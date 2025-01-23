package com.example.vatapp.response;

public class ProfileData {
    private String name;
    private String email;
    private String user_id;
    private String date_of_creation;
    private String phone_number;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getDate_of_creation() { return date_of_creation; }
    public void setDate_of_creation(String date_of_creation) { this.date_of_creation = date_of_creation; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
}
