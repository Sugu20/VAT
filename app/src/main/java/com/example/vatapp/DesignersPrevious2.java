package com.example.vatapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.AppCompatButton;

public class DesignersPrevious2 extends AppCompatActivity {

    private ImageView designImageView;
    private AppCompatButton viewInARButton;
    private ImageButton homeButton, backButton;
    private RecyclerView feedbackRecyclerView;
    private TextView feedbackTextView;
    private String previousActivity;  // To track the previous activity for back navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_previous2);

        // Initialize views
        designImageView = findViewById(R.id.designImageView);
        viewInARButton = findViewById(R.id.button6);
        FrameLayout homeButton = findViewById(R.id.homebutton);
        backButton = findViewById(R.id.backButton);
        feedbackRecyclerView = findViewById(R.id.recyclerView3);
        feedbackTextView = findViewById(R.id.textView32);

        // Set the design image (from your image selection or backend data)
        // designImageView.setImageURI(imageUri);  // Example: load image from URI

        // Handle "View in AR" button click
        viewInARButton.setOnClickListener(v -> openCameraForAR());

        // Handle "Home" button click (navigate to dash_designer)
        homeButton.setOnClickListener(v -> navigateToDashDesigner());

        // Handle "Back" button click based on previous activity
        backButton.setOnClickListener(v -> {
            if ("dash_user".equals(previousActivity)) {
                navigateToDashUser();
            } else {
                navigateToPreviousDesigner();
            }
        });

        // You may want to set the previous activity identifier based on how the user navigated here
        previousActivity = getIntent().getStringExtra("previous_activity");
    }

    // Method to open the camera for AR viewing
    private void openCameraForAR() {
        // Implement logic to open camera and AR functionality
        // For example, starting an AR camera activity
        Intent intent = new Intent(this, com.example.vatapp.ImageView.class);
        startActivity(intent);
    }

    // Method to navigate to dash_designer
    private void navigateToDashDesigner() {
        Intent intent = new Intent(this, dash_designer.class);
        startActivity(intent);
    }

    // Method to navigate to dash_user
    private void navigateToDashUser() {
        Intent intent = new Intent(this, dash_user.class);
        startActivity(intent);
    }

    // Method to navigate back to the previous designer list
    private void navigateToPreviousDesigner() {
        Intent intent = new Intent(this, designers_previous.class);
        startActivity(intent);
    }
}
