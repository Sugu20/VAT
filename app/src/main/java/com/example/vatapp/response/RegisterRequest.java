package com.example.vatapp.response;

public class RegisterRequest {
    private String name;
    private String role;
    private String email;
    private String phone_number;
    private String password;

    public RegisterRequest(String name, String role, String email, String phone_number, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }
}