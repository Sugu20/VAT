package com.example.vatapp; // replace with your app's package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class forgot_password extends AppCompatActivity {

    private EditText emailEditText, newPasswordEditText;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        // Initialize views
        emailEditText = findViewById(R.id.EmailAddressText7);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        continueButton = findViewById(R.id.button5);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String email = emailEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();


                if (email.isEmpty() || newPassword.isEmpty()) {
                    // You can show a Toast message or other feedback to the user if needed
                    return; // Exit the method if fields are empty
                }

                // Process the password reset (you can add your logic to send data to backend here)
                // For now, just simulate a successful reset

                // Once the password is reset, navigate back to the Sign In screen (signIn1 activity)
                Intent intent = new Intent(forgot_password.this, SignIn1.class); // replace SignInActivity with the correct class name
                startActivity(intent);

                // Optional: Close the ForgotPasswordActivity if you don't want it to stay in the back stack
                finish();
            }
        });
    }
}
