package com.example.vatapp; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.DesignersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class designers_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DesignerListAdapter adapter;
    private List<Designer> designerList;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_list); // Ensure this matches your XML filename

        recyclerView = findViewById(R.id.recyclerView3);
        homeButton = findViewById(R.id.imageView22);

        // Initialize RecyclerView
        designerList = new ArrayList<>();
        adapter = new DesignerListAdapter(designerList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Fetch designers from API (dummy data for now)
        fetchDesigners();

        // Set up home button to navigate back to dashboard
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(designers_list.this, dash_user.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }

    private void fetchDesigners() {
        ApiService ApiService = RetrofitClient.getClient().create(ApiService.class);

        ApiService.getDesigners().enqueue(new Callback<DesignersResponse>() {
            @Override
            public void onResponse(Call<DesignersResponse> call, Response<DesignersResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DesignersResponse designerResponse = response.body();

                    if ("success".equalsIgnoreCase(designerResponse.getStatus())) {
                        // Update the designer list
                        designerList.clear();
                        designerList.addAll(designerResponse.getData());
                        adapter.notifyDataSetChanged();

                        Toast.makeText(designers_list.this, "Fetched " + designerList.size() + " designers", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(designers_list.this, "Error: " + designerResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(designers_list.this, "Failed to fetch designers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DesignersResponse> call, Throwable t) {
                Toast.makeText(designers_list.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
