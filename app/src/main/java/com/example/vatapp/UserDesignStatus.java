package com.example.vatapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class UserDesignStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DesignRequestedAdapter adapter;
    private List<DesignRequestedClass> designList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design_status);

        recyclerView = findViewById(R.id.AllRequest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your design list
        designList = new ArrayList<>();

        // Fetch the data from the server using Retrofit
        fetchDesignRequests();
    }

    private void fetchDesignRequests() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<JsonObject> call = apiService.getUserRequests(userId, "false");  // Fetch basic data

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseData = response.body();
                    // Parse the response and populate the design list
                    DesignRequestedClass design = new Gson().fromJson(responseData, DesignRequestedClass.class);
                    designList.add(design);

                    // Set the adapter
                    adapter = new DesignRequestedAdapter(designList, design1 -> {
                        // Handle the item click (you can open another activity or dialog with design details)
                        openDesignDetails(design1);
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("UserDesignStatus", "Error fetching data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("UserDesignStatus", "Error: " + t.getMessage());
            }
        });
    }

    private String getUserIdFromSharedPreferences() {
        // Fetch the user_id from SharedPreferences or other storage
        return "";  // Example static user ID for testing
    }

    private void openDesignDetails(DesignRequestedClass design) {
        // Implement your logic to open the design details activity or show more information
        // Example: StartActivity with design data
    }
}
