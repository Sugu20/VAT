package com.example.vatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.IOException;

public class RequestNewDesign extends AppCompatActivity {

    private ImageView imageUploaded;
    private AppCompatButton uploadButton, sendButton;
    private ImageButton homeButton;
    private EditText descriptionEditText;
    private TextView textPrompt, textPrompt2;
    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_design);

        // Initialize views
        imageUploaded = findViewById(R.id.imageuploded);
        uploadButton = findViewById(R.id.Uploadbutton);
        sendButton = findViewById(R.id.button11);
        homeButton = findViewById(R.id.homebutton1);
        descriptionEditText = findViewById(R.id.editTextTextMultiLine2);
        textPrompt = findViewById(R.id.TextPrompt);
        textPrompt2 = findViewById(R.id.TextPrompt2);

        // Initialize Gallery Launcher
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri imageUri = result.getData().getData();
                try {
                    // Load the selected image into the ImageView
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageUploaded.setImageBitmap(bitmap);
                    imageUploaded.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Set Upload Button functionality
        uploadButton.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });

        // Set Send Button functionality
        sendButton.setOnClickListener(v -> {
            String description = descriptionEditText.getText().toString().trim();
            if (description.isEmpty() || imageUploaded.getVisibility() != View.VISIBLE) {
                // Show error if no image or description is provided
                descriptionEditText.setError("Please provide a description and upload an image.");
            } else {
                // Show success messages
                textPrompt.setVisibility(View.VISIBLE);
                textPrompt2.setVisibility(View.VISIBLE);

                // Hide other views
                imageUploaded.setVisibility(View.GONE);
                uploadButton.setVisibility(View.GONE);
                descriptionEditText.setVisibility(View.GONE);
                sendButton.setVisibility(View.GONE);
            }
        });

        // Set Home Button functionality
        homeButton.setOnClickListener(v -> {
            Log.d("HomeButton", "Home button clicked");
            Intent intent = new Intent(RequestNewDesign.this, dash_user.class);
            startActivity(intent);
            finish();
        });

    }
}
