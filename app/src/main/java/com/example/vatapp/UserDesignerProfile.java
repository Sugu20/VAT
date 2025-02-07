package com.example.vatapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class UserDesignerProfile extends AppCompatActivity {

    private Button uploadedDesignsButton, editProfileButton, logoutButton;
    private ImageButton homeButton;
    private TextView nameField, emailField, userIdField, dateOfCreationField, phoneField;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_designer_profile);

        // Initialize views
        uploadedDesignsButton = findViewById(R.id.button100);
        homeButton = findViewById(R.id.imageView22);
        nameField = findViewById(R.id.editTextText80);
        emailField = findViewById(R.id.editTextTextEmailAddress0);
        userIdField = findViewById(R.id.userid10);
        dateOfCreationField = findViewById(R.id.dateofcreation10);
        phoneField = findViewById(R.id.editTextTextMultiLine0);

        // Fetch user data from the backend
        fetchUserData();

        // Set up button click listeners
        uploadedDesignsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDesignerProfile.this, DesginersUserPrevious.class);
                intent.putExtra("user_id",""+getIntent().getStringExtra("user_id"));
                startActivity(intent);
            }
        });


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDesignerProfile.this, dash_user.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = getIntent().getStringExtra("user_id");

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ProfileData> call = apiService.fetchUserData(userId);

        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileData user = response.body();
                    userIdField.setText("User ID : " + user.getUser_id());
                    dateOfCreationField.setText("Date of Creation : " + user.getDate_of_creation());
                    phoneField.setText("Phone Number : "+user.getPhone_number());
                    nameField.setText("Name : "+user.getName());
                    emailField.setText("Gmail : "+user.getEmail());
                } else {
                    // Handle error
                    Toast.makeText(UserDesignerProfile.this, "Failed to load user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                // Handle failure (e.g., network issue)
                Toast.makeText(UserDesignerProfile.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
