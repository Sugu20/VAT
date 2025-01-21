package com.example.vatapp.response;


public class ForgotPasswordRequest {
    private String email;
    private String new_password;

    public ForgotPasswordRequest(String email, String new_password) {
        this.email = email;
        this.new_password = new_password;
    }
}
