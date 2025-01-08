package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize buttons
        AppCompatButton logoutButton = findViewById(R.id.button8);
        ImageButton homeButton = findViewById(R.id.imageView22);

        // Set onClickListener for Logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SignIn activity
                Intent intent = new Intent(UserProfile.this, SignIn.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

        // Set onClickListener for Home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to dash_user activity
                Intent intent = new Intent(UserProfile.this, dash_user.class);
                startActivity(intent);
            }
        });
    }
}
