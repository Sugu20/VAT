package com.example.vatapp; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.DesignerImagesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImportPage2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton homeButton;
    private DesignerImagesAdapter adapter;
    private List<Integer> designerImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_page2); // Ensure the XML file matches

        recyclerView = findViewById(R.id.recyclerView);
        homeButton = findViewById(R.id.imageView22);

        // Initialize the RecyclerView
        designerImages = new ArrayList<>();
        loadDesignerImages(); // Load images into the list
        adapter = new DesignerImagesAdapter(designerImages, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        // Set click listener for the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ImportPage2Activity.this, dash_designer.class);
                startActivity(homeIntent);
                finish(); // Close this activity
            }
        });

        // Feedback RecyclerView remains empty for now
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        // No adapter is set for now, as feedback is not available yet
    }

    private void loadDesignerImages() {
        // Example: Add random drawable resources uploaded by the designer
        designerImages.add(R.drawable.image1); // Replace with actual image resource IDs
        designerImages.add(R.drawable.image2);
        designerImages.add(R.drawable.image3);
        designerImages.add(R.drawable.image4);
    }
}
