package com.example.vatapp; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        // TODO: Replace with API integration
        // Dummy data for testing
        designerList.add(new Designer("Alice Johnson", "alice@example.com"));
        designerList.add(new Designer("Bob Smith", "bob@example.com"));
        designerList.add(new Designer("Catherine Lee", "catherine@example.com"));
        designerList.add(new Designer("Daniel Brown", "daniel@example.com"));

        // Notify adapter about data changes
        adapter.notifyDataSetChanged();

        // Show toast for testing
        Toast.makeText(this, "Fetched " + designerList.size() + " designers", Toast.LENGTH_SHORT).show();
    }
}
