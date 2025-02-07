package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup2 extends AppCompatActivity {

    private Button nextButton;
    private TextView signInText;
    private EditText nameInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        nextButton = findViewById(R.id.button3); // Button renamed from signUp to Next
        signInText = findViewById(R.id.textView3);
        nameInput = findViewById(R.id.editTextText9);
        emailInput = findViewById(R.id.editTextText2);
        phoneInput = findViewById(R.id.editTextText4);
        passwordInput = findViewById(R.id.editTextText5);
        confirmPasswordInput = findViewById(R.id.editTextText6);

        nextButton.setOnClickListener(v -> navigateToImageUpload());
    }

        private void navigateToImageUpload () {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Assume 'role' is determined from user selection (e.g., "user" or "designer")
//        String selectedRole = role; // Get this from user input

           // Get role from signup
            Intent intent = getIntent();
            String role = intent.getStringExtra("role");
// Ensure role is being passed correctly
            if (role == null) {
                Toast.makeText(this, "Role is missing!", Toast.LENGTH_SHORT).show();
            }

// When navigating to UploadProfileImage
            Intent nextIntent = new Intent(signup2.this, UploadProfileImage.class);
            nextIntent.putExtra("role", role); // Passing role forward
            nextIntent.putExtra("name", name);
            nextIntent.putExtra("email", email);
            nextIntent.putExtra("phone", phone);
            nextIntent.putExtra("password", password);
            startActivity(nextIntent);


            signInText.setOnClickListener(view-> startActivity(new Intent(signup2.this, SignIn1.class)));
            };

        }



