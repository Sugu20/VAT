package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class DesignersUserPrevious2 extends AppCompatActivity {

    private ImageView designImageView;
    private AppCompatButton viewInARButton;
    private ImageButton homeButton, backButton;
    private RecyclerView feedbackRecyclerView;
    private TextView feedbackTextView;

    String imageUrl;

    private String previousActivity;  // To track the previous activity for back navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_previous2);

        // Initialize views
        designImageView = findViewById(R.id.designImageView);
        viewInARButton = findViewById(R.id.button6);// Fixed the homeButton reference
        backButton = findViewById(R.id.backButton);
        feedbackRecyclerView = findViewById(R.id.recyclerView3);
        feedbackTextView = findViewById(R.id.textView32);


        imageUrl = Image_base_url + getIntent().getStringExtra("imageUrl");

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(designImageView);
        // Get the previous activity identifier
        previousActivity = getIntent().getStringExtra("previous_activity");

        // Handle "View in AR" button click
        viewInARButton.setOnClickListener(v -> {
            if (!imageUrl.isEmpty()) {
                openCameraForAR();
            }
        });

        // Handle "Back" button click (Navigate based on previous activity)
        backButton.setOnClickListener(v -> {
            if ("dash_user".equals(previousActivity)) {
                navigateToDashUser();
            } else {
                navigateToPreviousDesigner();
            }
        });
    }

    // Method to open the camera for AR viewing
    private void openCameraForAR() {
        Intent intent = new Intent(this, ARActivity.class);
        intent.putExtra("imageUrl",imageUrl);
        startActivity(intent);
    }

    // Method to navigate to DashUser
    private void navigateToDashUser() {
        Intent intent = new Intent(this, dash_user.class);
        startActivity(intent);
        finish(); // Close this activity
    }

    // Method to navigate back to the previous designer list
    private void navigateToPreviousDesigner() {
        Intent intent = new Intent(this, DesginersUserPrevious.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
