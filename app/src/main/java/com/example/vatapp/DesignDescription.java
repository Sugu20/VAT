package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.AcceptedResponse;
import com.example.vatapp.response.UploadedResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignDescription extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private TextView requesterNameTextView, descriptionTextView;
    private ImageView sampleImageView, finalDesignImageView;
    private Button acceptButton, rejectButton, uploadButton, submitButton;
    private ImageButton homeButton;
    private String requestId, acceptedId, requesterId;
    private Uri selectedImageUri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_description);

        // Initialize views
        requesterNameTextView = findViewById(R.id.requesterName);
        descriptionTextView = findViewById(R.id.multiLineText1);
        sampleImageView = findViewById(R.id.sampleImageView);
        finalDesignImageView = findViewById(R.id.finaldesign);
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        uploadButton = findViewById(R.id.uploadButton);
        submitButton = findViewById(R.id.submitbtn);
        homeButton = findViewById(R.id.homeButton);

        // Hide submit and upload buttons initially
        submitButton.setVisibility(View.GONE);
        uploadButton.setVisibility(View.GONE);

        // Get SharedPreferences data
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        acceptedId = sharedPreferences.getString("user_id", null);

        // Get intent data
        Intent intent = getIntent();
        if (intent != null) {
            requesterId = intent.getStringExtra("requester_id");
            String requesterName = intent.getStringExtra("requester_name");
            String description = intent.getStringExtra("description");
            String imageUrl = intent.getStringExtra("image_url");
            requestId = intent.getStringExtra("request_id");

            if (requesterName != null) {
                requesterNameTextView.setText("Name : " + requesterName);
            }
            if (description != null) {
                descriptionTextView.setText(description);
            }

            // Load image
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String loadImageUrl = Image_base_url + imageUrl;
                Glide.with(this)
                        .load(loadImageUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(sampleImageView);
            }

            boolean fromAccepted = intent.getBooleanExtra("from_accepted", false);
            if (fromAccepted) {
                acceptButton.setVisibility(View.GONE);
                uploadButton.setVisibility(View.VISIBLE);
            }
        }

        homeButton.setOnClickListener(v -> startActivity(new Intent(DesignDescription.this, DesignRequestList.class)));
        rejectButton.setOnClickListener(v -> rejectRequest());
        uploadButton.setOnClickListener(v -> openGallery());
        submitButton.setOnClickListener(v -> uploadImage());
        acceptButton.setOnClickListener(v -> acceptRequest());
    }

    private void acceptRequest() {
        String status = "Accepted"; // Set status to "Accepted"

        if (requestId == null || requestId.isEmpty()) {
            Toast.makeText(this, "Invalid Request ID", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<AcceptedResponse> call = apiService.updateRequestStatus(Integer.parseInt(requestId), Integer.parseInt(acceptedId), status);

        call.enqueue(new Callback<AcceptedResponse>() {
            @Override
            public void onResponse(Call<AcceptedResponse> call, Response<AcceptedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DesignDescription.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    acceptButton.setVisibility(View.GONE); // Hide accept button after acceptance
                    uploadButton.setVisibility(View.VISIBLE); // Show upload button after acceptance
                } else {
                    Toast.makeText(DesignDescription.this, "Failed to update status", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptedResponse> call, Throwable t) {
                Toast.makeText(DesignDescription.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rejectRequest() {
        String status = "Pending"; // Revert status

        if (requestId == null || requestId.isEmpty()) {
            Toast.makeText(this, "Invalid Request ID", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<AcceptedResponse> call = apiService.updateRequestStatus(Integer.parseInt(requestId), 0, status);

        call.enqueue(new Callback<AcceptedResponse>() {
            @Override
            public void onResponse(Call<AcceptedResponse> call, Response<AcceptedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DesignDescription.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after rejection
                } else {
                    Toast.makeText(DesignDescription.this, "Failed to update status", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptedResponse> call, Throwable t) {
                Toast.makeText(DesignDescription.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                finalDesignImageView.setImageURI(selectedImageUri);
                submitButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private void uploadImage() {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
            return;
        }

        File imageFile = new File(FileUtils.getPathFromUri(this, selectedImageUri));

        if (imageFile == null) {
            Toast.makeText(this, "Failed to process image.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);

        // Send only request_id as per the new PHP API
        RequestBody reqId = RequestBody.create(MediaType.parse("text/plain"), requestId);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<UploadedResponse> call = apiService.uploadImage(body, reqId);

        call.enqueue(new Callback<UploadedResponse>() {
            @Override
            public void onResponse(Call<UploadedResponse> call, Response<UploadedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DesignDescription.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    submitButton.setVisibility(View.GONE);
                } else {
                    Toast.makeText(DesignDescription.this, "Upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadedResponse> call, Throwable t) {
                Toast.makeText(DesignDescription.this, "Upload failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

