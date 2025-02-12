package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.FeedbackSubmitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDesignDetail extends AppCompatActivity {

    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design_detail);

        // Initialize views
        ImageView uploadedImg = findViewById(R.id.uploadedimg);
        TextView descriptionText = findViewById(R.id.editTextTextMultiLine2);
        TextView statusText = findViewById(R.id.statusText);
        TextView acceptedname = findViewById(R.id.acceptedname);
        ImageView designImageView = findViewById(R.id.image2);
        EditText rejectionNotes = findViewById(R.id.multiLineText1);
        androidx.appcompat.widget.AppCompatButton viewInARButton = findViewById(R.id.button7);
        androidx.appcompat.widget.AppCompatButton submitButton = findViewById(R.id.submitButton);
        androidx.appcompat.widget.AppCompatButton acceptButton = findViewById(R.id.acceptButton1);
        androidx.appcompat.widget.AppCompatButton rejectButton = findViewById(R.id.rejectButton1);
        LinearLayout acceptRejectLayout = findViewById(R.id.linear1);
        ImageButton homeButton = findViewById(R.id.homeButton5);

        // Fetch data passed via Intent
        String sampleImageUrl = getIntent().getStringExtra("sample_image");
        String designDescription = getIntent().getStringExtra("description");
        String designStatus = getIntent().getStringExtra("status");
        String uploadedImageUrl = getIntent().getStringExtra("upload_image");

        imageUrl = Image_base_url + uploadedImageUrl;

        if (getIntent().hasExtra("name")) {
            String acceptedName = getIntent().getStringExtra("name");
            acceptedname.setText("Accepted By: " + acceptedName);
        }

        // Load sample_image into uploadedImg with placeholder
        if (sampleImageUrl != null && !sampleImageUrl.isEmpty()) {
            String fullSampleUrl = Image_base_url + sampleImageUrl;
            Log.d("GlideDebug", "Sample Image URL: " + fullSampleUrl);  // Debug log

            Glide.with(this)
                    .load(fullSampleUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(uploadedImg);
        } else {
            Log.e("GlideError", "Sample image URL is null or empty.");
        }

        // Load upload_image into designImageView with placeholder
        if (uploadedImageUrl != null && !uploadedImageUrl.isEmpty()) {
            String fullUploadUrl = RetrofitClient.Image_base_url + uploadedImageUrl;
            Log.d("GlideDebug", "Uploaded Image URL: " + fullUploadUrl);  // Debug log

            Glide.with(this)
                    .load(fullUploadUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(designImageView);
        } else {
            Log.e("GlideError", "Uploaded image URL is null or empty.");
        }

        // Set design description
        descriptionText.setText(designDescription != null && !designDescription.isEmpty() ? designDescription : "No description provided.");

        // Set design status
        statusText.setText(designStatus != null ? designStatus : "Pending");

        // Show Accept/Reject buttons and View in AR button only if `upload_image` is available
        if (uploadedImageUrl != null && !uploadedImageUrl.isEmpty()) {
            acceptRejectLayout.setVisibility(View.VISIBLE);
            viewInARButton.setVisibility(View.VISIBLE);
        } else {
            acceptRejectLayout.setVisibility(View.GONE);
            viewInARButton.setVisibility(View.GONE);
        }

        // Hide rejection notes and submit button initially
        rejectionNotes.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);

        // Handle Reject Button Click
        rejectButton.setOnClickListener(v -> {
            rejectionNotes.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
        });

        // Handle Submit Button Click
        submitButton.setOnClickListener(v -> {
            String notes = rejectionNotes.getText().toString().trim();
            if (!notes.isEmpty()) {
                // Save rejection notes and navigate to dash_user
                Toast.makeText(this, "Rejection submitted.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserDesignDetail.this, dash_user.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please provide notes before submitting.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Home Button Click
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserDesignDetail.this, UserDesignStatus.class);
            startActivity(intent);
        });

        // View in AR Button Click (Leave navigation empty for now)
        viewInARButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ARActivity.class);
            intent.putExtra("imageUrl",imageUrl);
            startActivity(intent);
        });
    }

    // Submit feedback to the server
    private void submitFeedback(int userId, int imageId, String feedbackText) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<FeedbackSubmitResponse> call = apiService.submitFeedback(userId, imageId, feedbackText);

        call.enqueue(new Callback<FeedbackSubmitResponse>() {
            @Override
            public void onResponse(Call<FeedbackSubmitResponse> call, Response<FeedbackSubmitResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedbackSubmitResponse feedbackResponse = response.body();
                    Toast.makeText(UserDesignDetail.this, feedbackResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    if (feedbackResponse.isSuccess()) {
                        Intent intent = new Intent(UserDesignDetail.this, dash_user.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(UserDesignDetail.this, "Submission failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedbackSubmitResponse> call, Throwable t) {
                Toast.makeText(UserDesignDetail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
