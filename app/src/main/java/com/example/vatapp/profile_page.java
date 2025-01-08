package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize buttons
        AppCompatButton logoutButton = findViewById(R.id.button8);
        AppCompatButton uploadedDesignsButton = findViewById(R.id.button10);
        ImageButton homeButton = findViewById(R.id.imageView22);

        // Set onClickListener for Logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to sign_in_1 activity
                Intent intent = new Intent(profile_page.this, SignIn.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity
            }
        });

        // Set onClickListener for Uploaded Designs button
        uploadedDesignsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to designers_previous activity
                Intent intent = new Intent(profile_page.this, designers_previous.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to dash_designer activity
                Intent intent = new Intent(profile_page.this, dash_designer.class);
                startActivity(intent);
            }
        });
    }
}
