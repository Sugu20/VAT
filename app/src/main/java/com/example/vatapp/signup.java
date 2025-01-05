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
        setContentView(R.layout.activity_sign_up_1); // Ensure the layout file is named correctly

        // Initialize views
        userTypeSpinner = findViewById(R.id.spinner2);
        continueButton = findViewById(R.id.button);

        // Setup spinner with User and Designer options
        setupUserTypeSpinner();

        // Set continue button click listener
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleContinueButtonClick();
            }
        });
    }

    /**
     * Setup the spinner with User and Designer options.
     */
    private void setupUserTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_types, // Ensure this is defined in strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        userTypeSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parentView, View view, int position, long id) {
                selectedUserType = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parentView) {
                selectedUserType = null;
            }
        });
    }

    /**
     * Handle continue button click and navigate to Signup2.
     */
    private void handleContinueButtonClick() {
        if (selectedUserType == null) {
            Toast.makeText(this, "Please select a user type.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Navigate to Signup2 based on the selected user type
        Intent intent = new Intent(signup.this, signup2.class);

        // Optionally, pass the user type to the next activity
        intent.putExtra("USER_TYPE", selectedUserType);

        // Start the new activity
        startActivity(intent);
    }
}
