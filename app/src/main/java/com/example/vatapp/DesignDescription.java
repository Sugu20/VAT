package com.example.vatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vatapp.DesignRequestList;
import com.example.vatapp.dash_designer;

import java.io.IOException;

public class DesignDescription extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView sampleImageView;
    private ImageView finalDesignImageView;
    private EditText multiLineText;
    private String uploadedImageUrl; // URL for the uploaded design image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_description);

        // Initialize views
        sampleImageView = findViewById(R.id.sampleImageView);
        finalDesignImageView = findViewById(R.id.finaldesign);
        multiLineText = findViewById(R.id.multiLineText1);
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton backButton = findViewById(R.id.backButton);
        androidx.appcompat.widget.AppCompatButton uploadButton = findViewById(R.id.uploadButton);
        androidx.appcompat.widget.AppCompatButton acceptButton = findViewById(R.id.acceptButton);
        androidx.appcompat.widget.AppCompatButton rejectButton = findViewById(R.id.rejectButton);

        // Fetch data passed via Intent
        Intent intent = getIntent();
        String sampleImageUrl = intent.getStringExtra("sampleImageUrl");
        String designDescription = intent.getStringExtra("designDescription");

        // Load the sample image (if available)
        if (sampleImageUrl != null && !sampleImageUrl.isEmpty()) {
            Glide.with(this)
                    .load(sampleImageUrl)
                    .into(sampleImageView);
        }

        // Handle Accept Button
        acceptButton.setOnClickListener(v -> {
            Toast.makeText(this, "Design Accepted!", Toast.LENGTH_SHORT).show();
            // Move design to the "Accepted" fragment
            Intent resultIntent = new Intent();
            resultIntent.putExtra("status", "Accepted");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Handle Reject Button
        rejectButton.setOnClickListener(v -> {
            Toast.makeText(this, "Design Rejected!", Toast.LENGTH_SHORT).show();
            // Move design back to the "Pending" fragment
            Intent resultIntent = new Intent();
            resultIntent.putExtra("status", "Pending");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Handle Upload Button
        uploadButton.setOnClickListener(v -> openGallery());

        // Handle Home Button
        homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(DesignDescription.this, dash_designer.class);
            startActivity(homeIntent);
            finish();
        });

        // Handle Back Button
        backButton.setOnClickListener(v -> {
            Intent backIntent = new Intent(DesignDescription.this, DesignRequestList.class);
            startActivity(backIntent);
            finish();
        });
    }

    // Open gallery to select an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                try {
                    // Show the selected image in the final design ImageView
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    finalDesignImageView.setImageBitmap(bitmap);

                    // TODO: Handle upload logic to save the image to the server or database
                    Toast.makeText(this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
