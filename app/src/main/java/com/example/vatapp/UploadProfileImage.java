package com.example.vatapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.vatapp.FileUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.RegisterResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadProfileImage extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView6, profileImageView;
    private Button signUpButton;
    private Uri imageUri;

    private String name, email, phone, password, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_profile_image);

        imageView6 = findViewById(R.id.imageView6); // Initial add button
        profileImageView = findViewById(R.id.AfterUpload); // After selecting an image
        signUpButton = findViewById(R.id.button3); // Sign up button

        // Retrieve user data from intent
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");
        role = intent.getStringExtra("role");
        if (role == null) {
            Toast.makeText(this, "Role is missing in UploadProfileImage!", Toast.LENGTH_SHORT).show();
        }

        imageView6.setOnClickListener(v -> openFileChooser());
        signUpButton.setOnClickListener(v -> uploadUserWithImage());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);

            // Hide imageView6 and show profileImageView
            imageView6.setVisibility(View.GONE);
            profileImageView.setVisibility(View.VISIBLE);
        }
    }

    private void uploadUserWithImage() {
        if (imageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(FileUtils.getPathFromUri(this, imageUri));

        // Ensure the correct MIME type for the file
        String mimeType = getContentResolver().getType(imageUri);
        if (mimeType == null) {
            mimeType = "image/jpeg"; // Default to JPEG if MIME type is not available
        }
        Intent intent = getIntent();
         role = intent.getStringExtra("role");

        RequestBody requestFile = RequestBody.create(MediaType.parse(mimeType), file);
        MultipartBody.Part profileImage = MultipartBody.Part.createFormData("profile_image", file.getName(), requestFile);

        // Check for null values and use empty strings if null
        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), name != null ? name : "");
        RequestBody emailBody = RequestBody.create(MediaType.parse("text/plain"), email != null ? email : "");
        RequestBody phoneBody = RequestBody.create(MediaType.parse("text/plain"), phone != null ? phone : "");
        RequestBody passwordBody = RequestBody.create(MediaType.parse("text/plain"), password != null ? password : "");
        RequestBody roleBody = RequestBody.create(MediaType.parse("text/plain"), role != null ? role : "");

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.registerUserWithImage(nameBody, roleBody, emailBody, phoneBody, passwordBody, profileImage);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(UploadProfileImage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    // Navigate to login screen
                    Intent intent = new Intent(UploadProfileImage.this, SignIn1.class);
                    startActivity(intent);
                    finish(); // Close the current activity
                } else {
                    Toast.makeText(UploadProfileImage.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(UploadProfileImage.this, "Upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
