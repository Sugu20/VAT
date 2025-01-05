package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn1 extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private Button loginButton;
    private TextView signUpText, forgetPasswordText;  // Added forgetPasswordText
    private EditText userIdInput, passwordInput;
    private String selectedUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_2); // Ensure the layout file is named correctly

        // Initialize views
        userTypeSpinner = findViewById(R.id.spinner);
        loginButton = findViewById(R.id.button4);
        signUpText = findViewById(R.id.textView3);
        forgetPasswordText = findViewById(R.id.forgetPasswordtext);  // Initialize the forget password TextView
        userIdInput = findViewById(R.id.editTextText);
        passwordInput = findViewById(R.id.editTextText3);

        // Setup spinner
        setupSpinner();

        // Set login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        // Set sign up text click listener
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });

        // Set forget password click listener
        forgetPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgetPassword();
            }
        });
    }

    /**
     * Setup spinner with "User" and "Designer" options.
     */
    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_types, // Define this array in strings.xml
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUserType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedUserType = null;
            }
        });
    }

    /**
     * Handle login button click.
     */
    private void handleLogin() {
        String userId = userIdInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (userId.isEmpty() || password.isEmpty() || selectedUserType == null) {
            Toast.makeText(this, "Please fill in all fields and select a user type.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedUserType.equals("User")) {
            navigateToUserDashboard();
        } else if (selectedUserType.equals("Designer")) {
            navigateToDesignerDashboard();
        } else {
            Toast.makeText(this, "Invalid user type selected.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Navigate to the User Dashboard.
     */
    private void navigateToUserDashboard() {
        Intent intent = new Intent(SignIn1.this, dash_user.class); // Use dash_user class for User
        startActivity(intent);
    }

    /**
     * Navigate to the Designer Dashboard.
     */
    private void navigateToDesignerDashboard() {
        Intent intent = new Intent(SignIn1.this, dash_designer.class); // Use dash_designer class for Designer
        startActivity(intent);
    }

    /**
     * Navigate to the SignUp page.
     */
    private void navigateToSignUp() {
        Intent intent = new Intent(SignIn1.this, signup.class);
        startActivity(intent);
    }

    /**
     * Navigate to the Forget Password page.
     */
    private void navigateToForgetPassword() {
        Intent intent = new Intent(SignIn1.this, forgot_password.class); // Make sure to create ForgetPasswordActivity
        startActivity(intent);
    }
}
