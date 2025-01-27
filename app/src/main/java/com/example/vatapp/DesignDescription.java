package com.example.vatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.dash_designer;

import java.io.IOException;

public class DesignDescription extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button acceptButton, rejectButton, uploadButton;
    private ImageButton homeButton;
    private ImageView finalDesignImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_description);

        // Initialize views
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        uploadButton = findViewById(R.id.uploadButton);
        homeButton = findViewById(R.id.homeButton);
        finalDesignImageView = findViewById(R.id.finaldesign);

        // Hide upload button initially
        uploadButton.setVisibility(View.GONE);

        // Accept button click listener
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show upload button when accept button is clicked
                uploadButton.setVisibility(View.VISIBLE);
            }
        });

        // Reject button click listener
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide upload button when reject button is clicked
                uploadButton.setVisibility(View.GONE);
            }
        });

        // Upload button click listener
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to select an image
                openGallery();
            }
        });

        // Home button click listener
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DashDesignerActivity
                Intent intent = new Intent(DesignDescription.this, dash_designer.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method to open gallery
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Get the image from the selected URI
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                // Display the image in finaldesign ImageView
                finalDesignImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
