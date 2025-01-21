package com.example.vatapp; // replace with your app's package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ForgotPasswordRequest;
import com.example.vatapp.response.ForgotPasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                String email = emailEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();

                if (email.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(forgot_password.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Prepare the API request
                ForgotPasswordRequest request = new ForgotPasswordRequest(email, newPassword);
                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

                // Make the API call
                apiService.resetPassword(request).enqueue(new Callback<ForgotPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ForgotPasswordResponse apiResponse = response.body();

                            if ("success".equalsIgnoreCase(apiResponse.getStatus())) {
                                Toast.makeText(forgot_password.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                // Navigate back to Sign In screen
                                Intent intent = new Intent(forgot_password.this, SignIn1.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(forgot_password.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(forgot_password.this, "Failed to reset password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                        Toast.makeText(forgot_password.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
            }

    }

