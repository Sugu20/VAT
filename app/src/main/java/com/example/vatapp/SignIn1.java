package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.dash_designer;
import com.example.vatapp.dash_user;
import com.example.vatapp.forgot_password;
import com.example.vatapp.signup;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignIn1 extends AppCompatActivity {

    private EditText editTextPhoneNumber, editTextPassword;
    private Button loginButton;
    private TextView signUpNowText, forgetPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_2);

        // Initialize UI elements
        editTextPhoneNumber = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextText3);
        loginButton = findViewById(R.id.button4);
        signUpNowText = findViewById(R.id.textView3);
        forgetPasswordText = findViewById(R.id.forgetPasswordtext);

        // Set up login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        // Set up sign-up text click listener
        signUpNowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn1.this, signup.class);
                startActivity(intent);
            }
        });

        // Set up forget password text click listener
        forgetPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn1.this, forgot_password.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new thread for network operations
        new Thread(() -> {
            try {
                // URL of the PHP script
                URL url = new URL("http://localhost/vat_app/logIn.php");

                // Create HTTP connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // JSON data to send
                JSONObject postData = new JSONObject();
                postData.put("phone", phoneNumber);
                postData.put("password", password);

                // Write JSON to the request body
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = postData.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // Read the response
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }

                // Parse the response
                JSONObject responseJson = new JSONObject(response.toString());
                runOnUiThread(() -> {
                    try {
                        if ("success".equals(responseJson.getString("status"))) {
                            String role = responseJson.getString("role");

                            // Redirect to the appropriate dashboard
                            Intent intent;
                            if ("designer".equals(role)) {
                                intent = new Intent(SignIn1.this, dash_designer.class);
                            } else {
                                intent = new Intent(SignIn1.this, dash_user.class);
                            }
                            startActivity(intent);
                            Toast.makeText(SignIn1.this, "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignIn1.this, responseJson.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SignIn1.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                });

                // Close resources
                reader.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(SignIn1.this, "Network error", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

}
