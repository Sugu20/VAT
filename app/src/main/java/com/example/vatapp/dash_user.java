package com.example.vatapp; // Replace with your app's package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class dash_user extends AppCompatActivity {

    private TextView requestNewDesign, designStatus, profile, designersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user); // Ensure this matches your XML file name

        // Initialize views
        requestNewDesign = findViewById(R.id.txtNewDesign);
        designStatus = findViewById(R.id.textView21);
        profile = findViewById(R.id.textView24);
        designersList = findViewById(R.id.designers_list);

        // Set OnClickListener for "Request New Design"
        requestNewDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Design Request Page
                Intent intent = new Intent(dash_user.this, DesignRequestList.class); // Replace with the actual class name
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Design Status"
        designStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Design Status Page
                Intent intent = new Intent(dash_user.this, UserStatus.class); // Replace with the actual class name
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Profile"
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Profile Page
                Intent intent = new Intent(dash_user.this, profile_page.class); // Replace with the actual class name
                startActivity(intent);
            }
        });

        // Set OnClickListener for "Designer's List"
        designersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Designer's List Page
                Intent intent = new Intent(dash_user.this, designers_list.class); // Replace with the actual class name
                startActivity(intent);
            }
        });
    }
}
