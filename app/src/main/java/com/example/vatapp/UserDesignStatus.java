package com.example.vatapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.DesignStatusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class UserDesignStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DesignRequestedAdapter adapter;
    private List<DesignStatusResponse.Design> designList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("UserDesignStatus", "onCreate() is running");
        setContentView(R.layout.activity_user_design_status);

        recyclerView = findViewById(R.id.AllRequest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageButton homeButton = findViewById(R.id.homeButton);

        designList = new ArrayList<>();
        fetchDesignRequests();

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserDesignStatus.this, dash_user.class);
            startActivity(intent);
            finish();
        });
    }

    private void fetchDesignRequests() {
        Log.d("UserDesignStatus", "fetchDesignRequests() called");

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);
        Log.d("UserDesignStatus", "Fetched userId: " + userId);

        if (userId == null) {
            Log.e("UserDesignStatus", "User ID is null");
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<DesignStatusResponse> call = apiService.getUserRequests(userId);

        call.enqueue(new Callback<DesignStatusResponse>() {
            @Override
            public void onResponse(Call<DesignStatusResponse> call, Response<DesignStatusResponse> response) {
                Log.d("UserDesignStatus", "API Response received");
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("UserDesignStatus", "Response success: " + response.body().isSuccess());
                    designList = response.body().getRequests();
                    Log.d("UserDesignStatus", "Fetched designs: " + designList.size());

                    adapter = new DesignRequestedAdapter(designList, design -> openDesignDetails(design));
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("UserDesignStatus", "API Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<DesignStatusResponse> call, Throwable t) {
                Log.e("UserDesignStatus", "API Failure: " + t.getMessage());
            }
        });
    }

    private void openDesignDetails(DesignStatusResponse.Design design) {
        Intent intent = new Intent(this, UserDesignDetail.class);
        intent.putExtra("sample_image", design.getSampleImage() != null ? design.getSampleImage() : "");
        intent.putExtra("description", design.getDescription() != null ? design.getDescription() : "");
        intent.putExtra("status", design.getStatus() != null ? design.getStatus() : "");
        intent.putExtra("upload_image", design.getUploadImage() != null ? design.getUploadImage() : "");
        intent.putExtra("name", design.getAcceptedName() != null ? design.getAcceptedName() : "");
        startActivity(intent);
    }
}
