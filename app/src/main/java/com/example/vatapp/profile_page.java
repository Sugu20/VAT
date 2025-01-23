package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ProfileData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile_page extends AppCompatActivity {

    private Button uploadedDesignsButton, editProfileButton, logoutButton;
    private ImageButton homeButton;
    private TextView nameField, emailField, userIdField, dateOfCreationField, phoneField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        uploadedDesignsButton = findViewById(R.id.button10);
        editProfileButton = findViewById(R.id.button13);
        logoutButton = findViewById(R.id.button8);
        homeButton = findViewById(R.id.imageView22);
        nameField = findViewById(R.id.editTextText8);
        emailField = findViewById(R.id.editTextTextEmailAddress);
        userIdField = findViewById(R.id.userid1);
        dateOfCreationField = findViewById(R.id.dateofcreation1);
        phoneField = findViewById(R.id.editTextTextMultiLine);

        // Fetch user data from the backend
        fetchUserData();

        // Set up button click listeners
        uploadedDesignsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_page.this, designers_previous.class);
                startActivity(intent);
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_page.this, EditProfileActivity.class);
                intent.putExtra("name", nameField.getText().toString());
                intent.putExtra("email", emailField.getText().toString());
                startActivityForResult(intent, 100); // Request code 100 for editing profile
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_page.this, SignIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_page.this, dash_designer.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserData() {
        String userId = "12345"; // This should come from your session or logged-in user data

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ProfileData> call = apiService.fetchUserData(userId);

        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileData user = response.body();
                    userIdField.setText("User ID: " + user.getUser_id());
                    dateOfCreationField.setText("Date of Creation: " + user.getDate_of_creation());
                    phoneField.setText(user.getPhone_number());
                    nameField.setText(user.getName());
                    emailField.setText(user.getEmail());
                } else {
                    // Handle error
                    Toast.makeText(profile_page.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                // Handle failure (e.g., network issue)
                Toast.makeText(profile_page.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            // Update only name and email fields
            String updatedName = data.getStringExtra("updatedName");
            String updatedEmail = data.getStringExtra("updatedEmail");

            if (updatedName != null) {
                nameField.setText(updatedName);
            }
            if (updatedEmail != null) {
                emailField.setText(updatedEmail);
            }
        }
    }
}
