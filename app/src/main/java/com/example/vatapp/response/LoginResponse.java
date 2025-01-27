package com.example.vatapp.response;

public class LoginResponse {
    private String status;
    private String message;
    private int user_id;
    private long phone_number;
    private String role;

    // Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return user_id;
    }

    public long getPhoneNumber() {
        return phone_number;
    }

    public String getRole() {
        return role;
    }

    // Setters (optional, if needed)
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setPhoneNumber(long phone_number) {
        this.phone_number = phone_number;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
