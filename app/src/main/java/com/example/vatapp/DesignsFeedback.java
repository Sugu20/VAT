package com.example.vatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.FeedbackResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesignsFeedback extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DesignerFeedbackAdapter adapter;
    private List<FeedbackResponse.DesignersFeedback> feedbackList;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs_feedbacks);

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeButton = findViewById(R.id.imageView22);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(DesignsFeedback.this, dash_designer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        fetchFeedback(Integer.parseInt(userId));
    }

    private void fetchFeedback(int designerId) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<FeedbackResponse> call = apiService.getFeedback(designerId);

        call.enqueue(new Callback<FeedbackResponse>() {
            @Override
            public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess() && response.body().getFeedbacks() != null) {
                        feedbackList = response.body().getFeedbacks();
                        adapter = new DesignerFeedbackAdapter(DesignsFeedback.this, feedbackList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(DesignsFeedback.this, "No feedback found.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DesignsFeedback.this, "Failed to load feedback", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                Toast.makeText(DesignsFeedback.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



