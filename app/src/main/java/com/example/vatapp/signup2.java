package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup2 extends AppCompatActivity {

    private Button signUpButton;
    private TextView signInText;
    private EditText nameInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2); // Ensure this layout file exists

        // Initialize views
        signUpButton = findViewById(R.id.button3);
        signInText = findViewById(R.id.textView3);
        nameInput = findViewById(R.id.editTextText9);
        emailInput = findViewById(R.id.editTextText2);
        phoneInput = findViewById(R.id.editTextText4);
        passwordInput = findViewById(R.id.editTextText5);
        confirmPasswordInput = findViewById(R.id.editTextText6);

        // Set Sign Up button click listener
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp();
            }
        });

        // Set Sign In text click listener
        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignIn();
            }
        });
    }

    /**
     * Handle Sign Up button click.
     */
    private void handleSignUp() {
        // Get input values
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validation checks
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle successful sign up (e.g., save user data, etc.)
        // For now, just show a success message and navigate back to SignIn1
        Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show();

        // Navigate to Sign In page (SignIn1)
        navigateToSignIn();
    }

    /**
     * Navigate to the Sign In page (SignIn1).
     */
    private void navigateToSignIn() {
        Intent intent = new Intent(signup2.this, SignIn1.class);
        startActivity(intent);
        finish();  // Optional: to finish this activity and prevent the user from returning to Signup2
    }
}
