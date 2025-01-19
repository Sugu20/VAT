package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class dash_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);

        // Initialize TextViews
        TextView txtNewDesign = findViewById(R.id.txtNewDesign);
        TextView profile = findViewById(R.id.textView24);
        TextView designStatus = findViewById(R.id.textView21);
        TextView designersList = findViewById(R.id.designers_list);

        // Set click listeners
        txtNewDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Request New Design page
                Intent intent = new Intent(dash_user.this, RequestNewDesign.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to User Profile page
                Intent intent = new Intent(dash_user.this, UserProfile.class);
                startActivity(intent);
            }
        });

        designStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Design Status page
                Intent intent = new Intent(dash_user.this, UserDesignStatus.class);
                startActivity(intent);
            }
        });

        designersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Designers List page
                Intent intent = new Intent(dash_user.this, designers_list.class);
                startActivity(intent);
            }
        });
    }
}
