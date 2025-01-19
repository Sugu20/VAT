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

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup2 extends AppCompatActivity {

    private Button signUpButton;
    private TextView signInText;
    private EditText nameInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;

    private String userType;

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

        String role = getIntent().getStringExtra("USER_TYPE");
        sendToApi(name, role, phone, password, email);
    }


    private void sendToApi(String name, String role, String phone, String password, String email) {

        RegisterRequest request = new RegisterRequest(name, role, email, phone, password);

        Call<RegisterResponse> call = RetrofitClient.getInstance().create(ApiService.class).registerUser(request);


        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    if (Objects.equals(response.body().getStatus(), "success")) {

                        Toast.makeText(signup2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        navigateToSignIn();
                    } else {

                        Toast.makeText(signup2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(signup2.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });

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
