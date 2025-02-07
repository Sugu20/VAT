package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.vatapp.api.RetrofitClient;

public class FeedBackDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_description);

        // Get data from intent
        String feedbackText = getIntent().getStringExtra("feedback_text");
        String userName = getIntent().getStringExtra("user_name");
        String imageUrl = getIntent().getStringExtra("image_url");
        String finalImageUrl = Image_base_url+imageUrl;


        // Reference UI elements
        TextView textViewFeedback = findViewById(R.id.Feedbackshown);
        TextView textViewUserName = findViewById(R.id.GivenBy);
        ImageView imageViewFeedback = findViewById(R.id.ImageviewFeedback);

        // Set values
        textViewFeedback.setText(feedbackText);
        textViewUserName.setText("Feedback given by: " + userName);

        // Load image using Glide
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this).load(finalImageUrl).into(imageViewFeedback);
        }

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Home button
        FrameLayout homeBtn = findViewById(R.id.Homebtn);
        homeBtn.setOnClickListener(v -> {
            Intent homeIntent = new Intent(FeedBackDescription.this, dash_designer.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
