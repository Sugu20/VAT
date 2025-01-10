package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.DesignAdapter;
import com.example.vatapp.R;
import com.example.vatapp.dash_designer;

import java.util.ArrayList;
import java.util.List;

public class UserDesignStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton homeButton;
    private List<DesignRequest> designRequestList;
    private DesignAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design_status);

        // Initialize views
        recyclerView = findViewById(R.id.listView);
        homeButton = findViewById(R.id.imageView22);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        designRequestList = new ArrayList<>();
//        adapter = new DesignAdapter(designRequestList);
//        recyclerView.setAdapter(adapter);

        // Load design requests
        loadDesignRequests();

        // Handle Home Button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to designer dashboard
                Intent intent = new Intent(UserDesignStatus.this, dash_designer.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Mock method to load design requests.
     * Replace this with actual data fetching logic (e.g., API call).
     */
    private void loadDesignRequests() {
        // Example data
        designRequestList.add(new DesignRequest("Design 1", "Description for Design 1"));
        designRequestList.add(new DesignRequest("Design 2", "Description for Design 2"));
        designRequestList.add(new DesignRequest("Design 3", "Description for Design 3"));

        // Notify adapter about data changes
//        adapter.notifyDataSetChanged();
    }

    // Inner class to represent a design request (Model)
    public static class DesignRequest {
        private String title;
        private String description;

        public DesignRequest(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }



        }


