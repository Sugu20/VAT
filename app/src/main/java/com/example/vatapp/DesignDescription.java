package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.AcceptedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignDescription extends AppCompatActivity {

    private TextView requesterNameTextView, descriptionTextView;
    private ImageView sampleImageView;
    private Button acceptButton, rejectButton, uploadButton;
    private ImageButton homeButton;
    private String requestId;
    private String acceptedId;
    private boolean isAccepted = false; // To track button state

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_description);

        // Initialize views
        requesterNameTextView = findViewById(R.id.requesterName);
        descriptionTextView = findViewById(R.id.multiLineText1);
        sampleImageView = findViewById(R.id.sampleImageView);
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        uploadButton = findViewById(R.id.uploadButton);
        homeButton = findViewById(R.id.homeButton);

        // ✅ Ensure both Accept and Reject buttons are visible at first
        acceptButton.setVisibility(View.VISIBLE);
        rejectButton.setVisibility(View.VISIBLE);
        uploadButton.setVisibility(View.GONE); // Hide upload initially

        // Get SharedPreferences data
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        acceptedId = sharedPreferences.getString("user_id", null);

        // Get intent data
        Intent intent = getIntent();
        if (intent != null) {
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
        }

        homeButton.setOnClickListener(v -> startActivity(new Intent(DesignDescription.this, DesignRequestList.class)));

        acceptButton.setOnClickListener(v -> acceptRequest());
        rejectButton.setOnClickListener(v -> rejectRequest());
    }

    private void acceptRequest() {
        String status = "Accepted";

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
                    isAccepted = true;
                    updateButtonVisibility();
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
                    isAccepted = false;
                    updateButtonVisibility();
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

    private void updateButtonVisibility() {
        if (isAccepted) {
            acceptButton.setVisibility(View.GONE);  // Hide Accept after clicking
            rejectButton.setVisibility(View.VISIBLE); // Keep Reject visible
            uploadButton.setVisibility(View.VISIBLE); // Show Upload
        } else {
            acceptButton.setVisibility(View.VISIBLE); // Show Accept
            rejectButton.setVisibility(View.VISIBLE); // Show Reject at all times
            uploadButton.setVisibility(View.GONE); // Hide Upload when not accepted
        }
    }

}
