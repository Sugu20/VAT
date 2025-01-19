package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DesignersPrevious2 extends AppCompatActivity {

    private RecyclerView feedbackRecyclerView;
    private FeedbackAdapter feedbackAdapter;
    private List<String> feedbackList;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_previous2);

        // Initialize RecyclerView
        feedbackRecyclerView = findViewById(R.id.recyclerView3);
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load feedback data
        feedbackList = new ArrayList<>();
        loadFeedbackData();

        // Set adapter
        feedbackAdapter = new FeedbackAdapter(feedbackList);
        feedbackRecyclerView.setAdapter(feedbackAdapter);

        // Initialize buttons
        homeButton = findViewById(R.id.homebutton2);
        ImageButton backButton = findViewById(R.id.backButton);

        // Home button click listener
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(DesignersPrevious2.this, dash_designer.class);
            startActivity(intent);
            finish(); // Close current activity
        });

        // Back button click listener
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(DesignersPrevious2.this, designers_previous.class);
            startActivity(intent);
            finish(); // Close current activity
        });

        // View in AR button - leave empty
        findViewById(R.id.button6).setOnClickListener(v -> {
            Toast.makeText(this, "View in AR feature is under development.", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadFeedbackData() {
        // Dummy data for feedbacks
        feedbackList.add("Great design!");
        feedbackList.add("Love the colors!");
        feedbackList.add("Can you make the edges smoother?");
        feedbackList.add("Perfect for our project!");
        feedbackList.add("Looking forward to more designs like this.");

        // Replace with real data from your database or API if needed
    }
}
