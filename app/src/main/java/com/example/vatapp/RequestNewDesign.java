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
import androidx.appcompat.widget.AppCompatButton;

import java.io.IOException;

public class RequestNewDesign extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageUploaded;
    private AppCompatButton uploadButton, sendButton;
    private EditText descriptionInput;
    private TextView textPrompt, textPrompt2, requestNewDesign;
    private ImageButton homeButton;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_design);

        imageUploaded = findViewById(R.id.imageuploded);
        uploadButton = findViewById(R.id.Uploadbutton);
        sendButton = findViewById(R.id.button11);
        descriptionInput = findViewById(R.id.editTextTextMultiLine2);
        textPrompt = findViewById(R.id.TextPrompt);
        textPrompt2 = findViewById(R.id.TextPrompt2);
        requestNewDesign = findViewById(R.id.text25);
        homeButton = findViewById(R.id.homebutton1);

        uploadButton.setOnClickListener(v -> openGallery());

        sendButton.setOnClickListener(v -> submitDesignRequest());
    }

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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageUploaded.setImageBitmap(bitmap);
                imageUploaded.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void submitDesignRequest() {
        String description = descriptionInput.getText().toString().trim();

        if (description.isEmpty()) {
            Toast.makeText(this, "Please enter a description for the design.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hide other UI elements
        imageUploaded.setVisibility(View.GONE);
        uploadButton.setVisibility(View.GONE);
        sendButton.setVisibility(View.GONE);
        descriptionInput.setVisibility(View.GONE);
        findViewById(R.id.text26).setVisibility(View.GONE);
        findViewById(R.id.text27).setVisibility(View.GONE);

        // Show success message
        textPrompt.setVisibility(View.VISIBLE);
        textPrompt2.setVisibility(View.VISIBLE);
        requestNewDesign.setVisibility(View.VISIBLE);
        homeButton.setVisibility(View.VISIBLE);
    }
}
