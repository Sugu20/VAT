package com.example.vatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ProfileData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    private TextView nameField, emailField, phoneNumberField, userIdField, dateOfCreationField;
    private Button editProfileButton, logoutButton;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);  // The layout you provided

        // Initialize the views
        nameField = findViewById(R.id.editTextText8);
        emailField = findViewById(R.id.editTextTextEmailAddress);
        phoneNumberField = findViewById(R.id.phonenumber);
        userIdField = findViewById(R.id.userid);
        dateOfCreationField = findViewById(R.id.dateofcreation);

        editProfileButton = findViewById(R.id.button10);
        logoutButton = findViewById(R.id.button8);
        homeButton = findViewById(R.id.imageView22);

        // Fetch user data from backend or session
        fetchUserData();

        // Set up button click listeners
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, EditProfileActivity.class);
                intent.putExtra("name", nameField.getText().toString());
                intent.putExtra("email", emailField.getText().toString());
                startActivityForResult(intent, 100); // Request code 100 for editing profile
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log out the user and go back to sign in screen
                Intent intent = new Intent(UserProfile.this, SignIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to home/dashboard screen
                Intent intent = new Intent(UserProfile.this, dash_user.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);


        // Assuming ApiService and Retrofit are set up to fetch user data
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ProfileData> call = apiService.fetchUserData(userId);

        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileData user = response.body();
                    // Set user data into the respective fields
                    userIdField.setText("User ID : " + user.getUser_id());
                    dateOfCreationField.setText("Date of Creation : " + user.getDate_of_creation());
                    phoneNumberField.setText("Phone Number : " + user.getPhone_number());
                    nameField.setText("Name : "+user.getName());
                    emailField.setText("Gmail : "+user.getEmail());
                } else {
                    // Handle error if user data is not available or API response is not successful
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                // Handle failure (e.g., network issues)
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            // Get updated profile data and set it in the fields
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
