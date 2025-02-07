package com.example.vatapp;

public class Designer {
    private String user_id;
    private String name;
    private String email;

    public Designer(String userId, String name, String email) {
        this.user_id = userId;
        this.name = name;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

