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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vatapp.dash_user;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;

public class RequestNewDesign extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageUploaded;
    private MaterialButton uploadButton, sendButton;
    private EditText descriptionEditText;
    private TextView textPrompt, textPrompt2;
    private ImageButton homeButton;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_design);

        // Initialize views
        imageUploaded = findViewById(R.id.imageuploded);
        uploadButton = findViewById(R.id.Uploadbutton);
        sendButton = findViewById(R.id.button11);
        descriptionEditText = findViewById(R.id.editTextTextMultiLine2);
        textPrompt = findViewById(R.id.TextPrompt);
        textPrompt2 = findViewById(R.id.TextPrompt2);
        homeButton = findViewById(R.id.homebutton1);

        // Set onClickListener for Upload button
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Set onClickListener for Send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSendAction();
            }
        });

        // Set onClickListener for Home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDashboard();
            }
        });
    }

    // Open the gallery to select an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageUploaded.setImageBitmap(bitmap);
                imageUploaded.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle the Send button action
    private void handleSendAction() {
        String description = descriptionEditText.getText().toString().trim();

        if (imageUri == null) {
            Toast.makeText(this, "Please upload an image", Toast.LENGTH_SHORT).show();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hide other views and show the success messages
        imageUploaded.setVisibility(View.GONE);
        uploadButton.setVisibility(View.GONE);
        sendButton.setVisibility(View.GONE);
        descriptionEditText.setVisibility(View.GONE);
        textPrompt.setVisibility(View.VISIBLE);
        textPrompt2.setVisibility(View.VISIBLE);
    }

    // Navigate back to the dashboard
    private void navigateToDashboard() {
        Intent intent = new Intent(RequestNewDesign.this, dash_user.class);
        startActivity(intent);
        finish();
    }
}
