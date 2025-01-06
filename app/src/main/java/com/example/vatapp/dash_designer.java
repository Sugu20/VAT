package com.example.vatapp; // replace with your app's package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class dash_designer extends AppCompatActivity {

    private TextView uploadDesignTextView, profileTextView, designRequestsTextView, designersPreviousUploadTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_designer); // replace with the correct layout file name

        // Initialize views
        uploadDesignTextView = findViewById(R.id.txtNewDesign);
        profileTextView = findViewById(R.id.textView24);
        designRequestsTextView = findViewById(R.id.textView21);
        designersPreviousUploadTextView = findViewById(R.id.designers_list);

        // Set OnClickListener for "Upload Design"
        uploadDesignTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Import Page
                Intent intent = new Intent(dash_designer.this, import_Page.class); // replace with actual class name for import page
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Profile"
        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Profile Page
                Intent intent = new Intent(dash_designer.this, profile_page.class); // replace with actual class name for profile page
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Design Requests"
        designRequestsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Design Requests Page
                Intent intent = new Intent(dash_designer.this, DesignRequestList.class); // replace with actual class name for design requests page
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Designer's Previous Upload"
        designersPreviousUploadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Designer's Previous Upload Page
                Intent intent = new Intent(dash_designer.this, designers_previous.class); // replace with actual class name for previous uploads page
                startActivity(intent);
            }
        });
    }
}
