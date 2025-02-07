package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private Button continueButton;
    private String selectedUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_1);

        // Initialize views
        userTypeSpinner = findViewById(R.id.spinner2);
        continueButton = findViewById(R.id.button);

        // Setup spinner
        setupUserTypeSpinner();

        // Set continue button click listener
        continueButton.setOnClickListener(v -> handleContinueButtonClick());
    }

    private void setupUserTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_types, // Ensure this exists in strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        // Set default selection to avoid null values
        selectedUserType = userTypeSpinner.getSelectedItem().toString();
    }

    private void handleContinueButtonClick() {
        if (selectedUserType == null || selectedUserType.isEmpty()) {
            Toast.makeText(this, "Please select a user type.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Determine the role
        String role;
        if (selectedUserType.trim().equalsIgnoreCase("User")) {
            role = "user";
        } else if (selectedUserType.trim().equalsIgnoreCase("Designer")) {
            role = "designer";
        } else {
            Toast.makeText(this, "Invalid selection.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Navigate to signup2 with role
        Intent intent = new Intent(signup.this, signup2.class);
        intent.putExtra("role", role);
        startActivity(intent);
    }
}
