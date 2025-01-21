package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

public class  UserProfile extends AppCompatActivity {

    private TextView userId, dateOfCreation, accountDetails;
    private EditText nameField, phoneField, emailField;
    private AppCompatButton editProfileButton, logoutButton;
    private ConstraintLayout homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        userId = findViewById(R.id.userid);
        dateOfCreation = findViewById(R.id.dateofcreation);
        accountDetails = findViewById(R.id.accountdetails);
        TextView nameField = findViewById(R.id.editTextText8);
        TextView phoneField = findViewById(R.id.phonenumber);
        TextView emailField = findViewById(R.id.editTextTextEmailAddress);
        editProfileButton = findViewById(R.id.button10);
        logoutButton = findViewById(R.id.button8);
        FrameLayout homeButton = findViewById(R.id.homebutton);

        // Load user details
        loadUserDetails();

        // Home button listener
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfile.this, dash_user.class);
            startActivity(intent);
            finish();
        });

        // Logout button listener
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfile.this, SignIn.class);
            startActivity(intent);
            finish();
        });

        // Edit profile button listener
        editProfileButton.setOnClickListener(v -> {
            nameField.setEnabled(true);
            phoneField.setEnabled(true);
            emailField.setEnabled(false); // Email remains uneditable

            // Save changes when user clicks edit again
            if (editProfileButton.getText().toString().equals("Save Changes")) {
                saveUserDetails();
                nameField.setEnabled(false);
                phoneField.setEnabled(false);
                editProfileButton.setText("Edit Profile");
            } else {
                editProfileButton.setText("Save Changes");
            }
        });
    }

    private void loadUserDetails() {
        // Mock data, replace this with actual data retrieval
        userId.setText("User ID: 123456");
        dateOfCreation.setText("Date Created: 2023-01-01");
        accountDetails.setText("Account Details:");

//        nameField.setText("John Doe");
//        phoneField.setText("9876543210");
//        emailField.setText("john.doe@example.com");

        // Disable fields initially
//        nameField.setEnabled(false);
//        phoneField.setEnabled(false);
//        emailField.setEnabled(false);
    }

    private void saveUserDetails() {
        // Save changes locally or to the database
        String updatedName = nameField.getText().toString();
        String updatedPhone = phoneField.getText().toString();

        // Update logic here (e.g., send to database or shared preferences)
        // ...

        // Feedback for the user (optional)
        showMessage("Profile updated successfully!");
    }

    private void showMessage(String message) {
        // Display a short feedback message
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
