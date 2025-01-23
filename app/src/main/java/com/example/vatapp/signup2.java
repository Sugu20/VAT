package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup2 extends AppCompatActivity {

    private Button signUpButton;
    private TextView signInText;
    private EditText nameInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        signUpButton = findViewById(R.id.button3);
        signInText = findViewById(R.id.textView3);
        nameInput = findViewById(R.id.editTextText9);
        emailInput = findViewById(R.id.editTextText2);
        phoneInput = findViewById(R.id.editTextText4);
        passwordInput = findViewById(R.id.editTextText5);
        confirmPasswordInput = findViewById(R.id.editTextText6);

        signUpButton.setOnClickListener(v -> handleSignUp());
        signInText.setOnClickListener(v -> navigateToSignIn());
    }

    private void handleSignUp() {
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

        RegisterRequest request = new RegisterRequest(name, "user", email, phone, password);

        Call<RegisterResponse> call = RetrofitClient.getClient().create(ApiService.class).registerUser(request);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        Toast.makeText(signup2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        navigateToSignIn();
                    } else {
                        Toast.makeText(signup2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(signup2.this, "Registration failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToSignIn() {
        Intent intent = new Intent(signup2.this, SignIn1.class);
        startActivity(intent);
        finish();
    }
}
