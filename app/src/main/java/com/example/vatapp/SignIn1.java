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

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn1 extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private Button loginButton;
    private TextView signUpText, forgetPasswordText;
    private EditText userIdInput, passwordInput;
    private String selectedUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_2);

        // Initialize views
        userTypeSpinner = findViewById(R.id.spinner);
        loginButton = findViewById(R.id.button4);
        signUpText = findViewById(R.id.textView3);
        forgetPasswordText = findViewById(R.id.forgetPasswordtext);
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
                R.array.user_types,
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

        // Call the SignIn1 method for API login
        signInApi(userId, password);
    }

    /**
     * API login method to authenticate the user.
     */
    private void signInApi(String userId, String password) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        LoginRequest request = new LoginRequest(userId, password);
        Call<LoginResponse> call = apiService.loginUser(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String status = response.body().getStatus();
                    String message = response.body().getMessage();
                    String userId = response.body().getUserId();
                    String role = response.body().getRole();

                    if ("success".equals(status)) {
                        Toast.makeText(SignIn1.this, "Login Successful! Role: " + role, Toast.LENGTH_SHORT).show();

                        // Navigate based on role or user type
                        if ("user".equalsIgnoreCase(role)) {
                            navigateToUserDashboard();
                        } else if ("designer".equalsIgnoreCase(role)) {
                            navigateToDesignerDashboard();
                        }
                    } else {
                        Toast.makeText(SignIn1.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIn1.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignIn1.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

        private void navigateToUserDashboard() {
        Intent intent = new Intent(SignIn1.this, dash_user.class);
        startActivity(intent);
    }

    private void navigateToDesignerDashboard() {
        Intent intent = new Intent(SignIn1.this, dash_designer.class);
        startActivity(intent);
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(SignIn1.this, signup.class);
        startActivity(intent);
    }

    private void navigateToForgetPassword() {
        Intent intent = new Intent(SignIn1.this, forgot_password.class);
        startActivity(intent);
    }
}
