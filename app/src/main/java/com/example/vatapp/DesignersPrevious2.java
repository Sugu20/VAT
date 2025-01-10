package com.example.vatapp; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DesignersPrevious2 extends AppCompatActivity {

    private RecyclerView imageRecyclerView, feedbackRecyclerView;
    private ImageGridAdapter imageAdapter; // Adapter for displaying the selected image
    private FeedbackAdapter feedbackAdapter; // Adapter for displaying feedback
    private List<String> selectedImageList;
    private List<Feedback> feedbackList;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_previous2); // Ensure this matches your XML filename

//        imageRecyclerView = findViewById(R.id.recyclerView2);
//        feedbackRecyclerView = findViewById(R.id.recyclerView3);
//        homeButton = findViewById(R.id.imageView22);

        // Set up RecyclerView for the selected image
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        selectedImageList = new ArrayList<>();
        imageAdapter = new ImageGridAdapter(selectedImageList, this);
        imageRecyclerView.setAdapter(imageAdapter);

        // Set up RecyclerView for feedback
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackList = new ArrayList<>();
//        feedbackAdapter = new FeedbackAdapter(feedbackList, this);
//        feedbackRecyclerView.setAdapter(feedbackAdapter);

        // Fetch selected image and feedback
        fetchSelectedImage();
        fetchFeedback();

        // "View in AR" button functionality
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesignersPrevious2.this, "View in AR clicked!", Toast.LENGTH_SHORT).show();
                // TODO: Add AR integration logic here
            }
        });

        // Home button navigation
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(DesignersPrevious2.this, dash_designer.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }

    private void fetchSelectedImage() {
        // TODO: Replace with actual data passed from DesignersPreviousActivity
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl"); // Image URL passed from previous activity

        if (imageUrl != null) {
            selectedImageList.add(imageUrl);
        } else {
            // Dummy data for testing
            selectedImageList.add("https://example.com/selected_image.jpg");
        }

        // Notify adapter about data changes
        imageAdapter.notifyDataSetChanged();
    }

    private void fetchFeedback() {
        // TODO: Replace with API call to fetch feedback for the design
//        feedbackList.add(new Feedback("User123", "Great design!"));
//        feedbackList.add(new Feedback("User456", "Can you tweak the colors?"));
//        feedbackList.add(new Feedback("User789", "Amazing work!"));
//
//        // Notify adapter about data changes
//        feedbackAdapter.notifyDataSetChanged();
    }
}
