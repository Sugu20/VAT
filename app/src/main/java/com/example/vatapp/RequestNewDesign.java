package com.example.vatapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.IOException;

public class RequestNewDesign extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private ImageView imageUploaded;
    private AppCompatButton uploadButton, sendButton;
    private EditText descriptionField;
    private TextView text26, text27, textPrompt, textPrompt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_design);

        // Initialize UI elements
        imageUploaded = findViewById(R.id.imageuploded);
        uploadButton = findViewById(R.id.Uploadbutton);
        sendButton = findViewById(R.id.button11);
        descriptionField = findViewById(R.id.editTextTextMultiLine2);
        text26 = findViewById(R.id.text26);
        text27 = findViewById(R.id.text27);
        textPrompt = findViewById(R.id.TextPrompt);
        textPrompt2 = findViewById(R.id.TextPrompt2);
        AppCompatButton homeButton = findViewById(R.id.homebutton1);

        // Set up click listener for upload button
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to pick an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        // Set up click listener for send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionField.getText().toString().trim();

                if (description.isEmpty()) {
                    descriptionField.setError("Description is required!");
                } else {
                    // Hide unnecessary elements
                    text26.setVisibility(View.GONE);
                    text27.setVisibility(View.GONE);
                    imageUploaded.setVisibility(View.GONE);
                    descriptionField.setVisibility(View.GONE);
                    uploadButton.setVisibility(View.GONE);
                    sendButton.setVisibility(View.GONE);

                    // Show success prompts
                    textPrompt.setVisibility(View.VISIBLE);
                    textPrompt2.setVisibility(View.VISIBLE);
                }
            }
        });

        // Set up click listener for home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to dash_user activity
                Intent intent = new Intent(RequestNewDesign.this, dash_user.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imageUploaded.setImageBitmap(bitmap);
                    imageUploaded.setVisibility(View.VISIBLE);

                    // Hide the upload button
                    uploadButton.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
