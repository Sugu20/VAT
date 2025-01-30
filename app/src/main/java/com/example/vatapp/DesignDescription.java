package com.example.vatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DesignDescription extends AppCompatActivity {

    private TextView requesterNameTextView, descriptionTextView;
    private ImageView sampleImageView;
    private Button acceptButton, rejectButton, uploadButton;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_description);

        // Initialize views
//        requesterNameTextView = findViewById(R.id.requesterNameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        sampleImageView = findViewById(R.id.sampleImageView); // Make sure this exists in XML
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        uploadButton = findViewById(R.id.uploadButton);
        homeButton = findViewById(R.id.homeButton);

        // Get intent data
        Intent intent = getIntent();
        if (intent != null) {
            String requesterName = intent.getStringExtra("requester_name");
            String description = intent.getStringExtra("description");
            String imageUrl = intent.getStringExtra("image_url");

            // Set values to views
            requesterNameTextView.setText(requesterName);
            descriptionTextView.setText(description);

            // Load image
            if (imageUrl != null && !imageUrl.isEmpty()) {
                loadImage(imageUrl);
            }
        }
    }

    private void loadImage(String imageUrl) {
        // Check if the image is Base64 encoded
        if (imageUrl.startsWith("/")) {
            // Image is likely stored on the server with a path
            imageUrl = "https://53e3-2401-4900-1cc9-e4d6-dc0d-3e8-11fa-3857.ngrok-free.app" + imageUrl; // Replace with your actual server URL
//            new ImageLoader(sampleImageView).execute(imageUrl);
        } else {
            // If it's a Base64 string, decode and display it
            try {
                byte[] decodedString = Base64.decode(imageUrl, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                sampleImageView.setImageBitmap(decodedByte);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
