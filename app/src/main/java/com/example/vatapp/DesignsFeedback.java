package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignsFeedback extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DesignerFeedbackAdapter adapter;
    private List<com.example.vatapp.DesignersFeedback> feedbackList;
    private ImageButton homeButton; // Added Home Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs_feedback);

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeButton = findViewById(R.id.imageView22); // Reference Home Button

        // Home button click listener
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(DesignsFeedback.this, dash_designer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Replace with actual designer_id and image_id
        int designerId = 1;
        int imageId = 101;

        fetchFeedback(designerId, imageId);
    }

    private void fetchFeedback(int designerId, int imageId) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<com.example.vatapp.response.FeedbackResponse> call = apiService.getFeedback(designerId, imageId);

        call.enqueue(new Callback<com.example.vatapp.response.FeedbackResponse>() {
            @Override
            public void onResponse(Call<com.example.vatapp.response.FeedbackResponse> call, Response<com.example.vatapp.response.FeedbackResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        feedbackList = response.body().getFeedbacks();
                        adapter = new DesignerFeedbackAdapter(DesignsFeedback.this, feedbackList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(DesignsFeedback.this, "No feedback found.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DesignsFeedback.this, "Failed to load feedback", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.vatapp.response.FeedbackResponse> call, Throwable t) {
                Toast.makeText(DesignsFeedback.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}
