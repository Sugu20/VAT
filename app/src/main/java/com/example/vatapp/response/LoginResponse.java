package com.example.vatapp.response;

public class LoginResponse {
    private String status;
    private String message;
    private String user_id; // Add user_id
    private String role;    // Add role

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Getter for user_id
    public String getUserId() {
        return user_id;
    }

    // Getter for role
    public String getRole() {
        return role;
    }
}
