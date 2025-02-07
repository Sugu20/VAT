package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UserDesignDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design_detail);

        // Initialize views
        ImageView uploadedImg = findViewById(R.id.uploadedimg);
        TextView descriptionText = findViewById(R.id.text27);
        TextView statusText = findViewById(R.id.statusText);
        ImageView designImageView = findViewById(R.id.image2);
        EditText rejectionNotes = findViewById(R.id.multiLineText1);
        androidx.appcompat.widget.AppCompatButton viewInARButton = findViewById(R.id.button7);
        androidx.appcompat.widget.AppCompatButton submitButton = findViewById(R.id.submitButton);
        androidx.appcompat.widget.AppCompatButton acceptButton = findViewById(R.id.acceptButton1);
        androidx.appcompat.widget.AppCompatButton rejectButton = findViewById(R.id.rejectButton1);
        ImageButton homeButton = findViewById(R.id.homeButton);
        LinearLayout acceptRejectLayout = findViewById(R.id.linear1);

        // Fetch data passed via Intent
        String uploadedImageUrl = getIntent().getStringExtra("imageUrl");
        String designDescription = getIntent().getStringExtra("description");
        String designStatus = getIntent().getStringExtra("status");
        String designerImageUrl = getIntent().getStringExtra("designerImageUrl"); // Designer's uploaded design URL

        // Load uploaded image (if available)
        if (uploadedImageUrl != null && !uploadedImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(uploadedImageUrl)
                    .into(uploadedImg);
        } else {
            uploadedImg.setVisibility(View.GONE);
        }

        // Set design description
        if (designDescription != null && !designDescription.isEmpty()) {
            descriptionText.setText(designDescription);
        } else {
            descriptionText.setText("No description provided.");
        }

        // Set design status
        statusText.setText(designStatus != null ? designStatus : "Pending");

        // Initially hide Accept/Reject buttons and View in AR button
        acceptRejectLayout.setVisibility(View.GONE);
        viewInARButton.setVisibility(View.GONE);

        // Load design image (if uploaded by the designer)
        if (designerImageUrl != null && !designerImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(designerImageUrl)
                    .into(designImageView);

            // Show Accept/Reject buttons and View in AR button since final design exists
            acceptRejectLayout.setVisibility(View.VISIBLE);
            viewInARButton.setVisibility(View.VISIBLE);
        } else {
            designImageView.setVisibility(View.GONE);
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
            Intent intent = new Intent(UserDesignDetail.this, dash_user.class);
            startActivity(intent);
            finish();
        });

        // View in AR Button Click (Leave navigation empty for now)
        viewInARButton.setOnClickListener(v -> {
            Toast.makeText(this, "View in AR is currently under development.", Toast.LENGTH_SHORT).show();
        });
    }
}
