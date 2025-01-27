package com.example.vatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.dash_designer;
import com.example.vatapp.dash_user;
import com.example.vatapp.forgot_password;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;
import com.example.vatapp.signup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        loginButton.setOnClickListener(v -> handleLogin());

        // Set up sign-up text click listener
        signUpNowText.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn1.this, signup.class);
            startActivity(intent);
        });

        // Set up forget password text click listener
        forgetPasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn1.this, forgot_password.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Retrofit instance and ApiService
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Create the request object
        LoginRequest loginRequest = new LoginRequest(phoneNumber, password);

        // Make the API call
        Call<LoginResponse> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if ("success".equals(loginResponse.getStatus())) {
                        String role = loginResponse.getRole();
                        String userId = String.valueOf(loginResponse.getUserId());

                        // Save user_id to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_id", userId); // Store userId directly as a string
                        editor.apply();


                        // Redirect to the appropriate dashboard
                        Intent intent;
                        if ("designer".equalsIgnoreCase(role.trim())) {
                            intent = new Intent(SignIn1.this, dash_designer.class);
                        } else if ("user".equalsIgnoreCase(role.trim())) {
                            intent = new Intent(SignIn1.this, dash_user.class);
                        } else {
                            Toast.makeText(SignIn1.this, "Unknown role: " + role, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        startActivity(intent);
                        Toast.makeText(SignIn1.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn1.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIn1.this, "Invalid response from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignIn1.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
