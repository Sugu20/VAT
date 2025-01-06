package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImportPage2Activity extends AppCompatActivity {

    private ImageView selectedImageView;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_page2); // Ensure the layout file matches

        // Initialize views
        selectedImageView = findViewById(R.id.imageView10); // Matches the ImageView ID in XML
        homeButton = findViewById(R.id.imageView22); // Matches the home button ID in XML

        // Get the selected image resource ID from the Intent
        Intent intent = getIntent();
        int selectedImageResId = intent.getIntExtra("selected_image_res_id", R.drawable.rectangle_84); // Default fallback

        // Set the image in the ImageView
        selectedImageView.setImageResource(selectedImageResId);

        // Set click listener for the home button
        homeButton.setOnClickListener(view -> {
            Intent homeIntent = new Intent(ImportPage2Activity.this, dash_designer.class);
            startActivity(homeIntent);
            finish(); // Close this activity
        });
    }
}
