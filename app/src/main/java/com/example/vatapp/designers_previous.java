package com.example.vatapp ; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.ImageGridAdapter;
import com.example.vatapp.dash_designer;
import com.example.vatapp.designers_list;

import java.util.ArrayList;
import java.util.List;

public class designers_previous extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.vatapp.ImageGridAdapter  adapter;
    private List<String> imageList;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_previous); // Ensure this matches your XML filename

        recyclerView = findViewById(R.id.recyclerView1);
        homeButton = findViewById(R.id.imageView22);

        // Initialize RecyclerView with Grid Layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 columns for the grid
        imageList = new ArrayList<>();
        adapter = new ImageGridAdapter(imageList, this);
        recyclerView.setAdapter(adapter);

        // Fetch images (dummy data for now)
        fetchImages();

        // Home button click event
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(designers_previous.this, dash_designer.class);
                startActivity(homeIntent);
                finish();
            }
        });

        // Item click event for RecyclerView
        adapter.setOnItemClickListener(new ImageGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent detailIntent = new Intent(designers_previous.this, DesignersPrevious2.class);
                detailIntent.putExtra("imageUrl", imageList.get(position)); // Pass image URL to next activity
                startActivity(detailIntent);
            }
        });
    }

    private void fetchImages() {
        // TODO: Replace with API integration to fetch uploaded images
        // Dummy data (URLs or paths of images)
        imageList.add("https://example.com/image1.jpg");
        imageList.add("https://example.com/image2.jpg");
        imageList.add("https://example.com/image3.jpg");
        imageList.add("https://example.com/image4.jpg");
        imageList.add("https://example.com/image5.jpg");

        // Notify adapter about data changes
        adapter.notifyDataSetChanged();

        // Toast for debugging
        Toast.makeText(this, "Fetched " + imageList.size() + " images", Toast.LENGTH_SHORT).show();
    }
}
