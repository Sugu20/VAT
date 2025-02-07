package com.example.vatapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.ImageGridAdapter;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.dash_designer;
import com.example.vatapp.response.DesignResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesginersUserPrevious extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.vatapp.ImageGridAdapter adapter;
    private List<String> imageList;
    private ImageButton homeButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desginers_user_previous); // Ensure this matches your XML filename

        recyclerView = findViewById(R.id.recyclerView11);
        homeButton = findViewById(R.id.imageView22);

        // Initialize RecyclerView with Grid Layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 columns for the grid
        imageList = new ArrayList<>();
        adapter = new ImageGridAdapter(imageList, this);
        recyclerView.setAdapter(adapter);

        // Fetch images
        fetchImages();

        // Home button click event
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(DesginersUserPrevious.this, dash_user.class);
                startActivity(homeIntent);
                finish();
            }
        });

        // Item click event for RecyclerView
        adapter.setOnItemClickListener(new ImageGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent detailIntent = new Intent(DesginersUserPrevious.this, DesignersUserPrevious2.class);
                detailIntent.putExtra("imageUrl", imageList.get(position));
                // Pass image URL to next activity
                startActivity(detailIntent);
            }
        });
    }

    private void fetchImages() {
        // Retrieve user_id (as a String from SharedPreferences)
        String userId = getUserId();

        if (userId == null) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Pass the user_id as a query parameter
        apiService.getDesigns(Integer.parseInt(userId)).enqueue(new Callback<DesignResponse>() {
            @Override
            public void onResponse(Call<DesignResponse> call, Response<DesignResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DesignResponse.Design> designs = response.body().getDesigns();

                    // Update the imageList with URLs from the API
                    imageList.clear();
                    for (DesignResponse.Design design : designs) {
                        imageList.add(design.getFile_path());
                    }

                    // Notify adapter about data changes
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DesginersUserPrevious.this, "Failed to fetch designs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DesignResponse> call, Throwable t) {
                Toast.makeText(DesginersUserPrevious.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUserId() {
//        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return getIntent().getStringExtra("user_id");  // Default value is null if the user ID is not found
    }
}
