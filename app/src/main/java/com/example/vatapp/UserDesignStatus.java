package com.example.vatapp;

import android.content.Intent;  // For Intent to navigate to another activity
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import android.view.View;
import android.widget.ImageButton;

public class UserDesignStatus extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton homeButton;  // Declare home button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_design_status); // Your layout

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        homeButton = findViewById(R.id.imageView22);  // Get reference to home button

        // Set up the ViewPager and Adapter
        setupViewPager(viewPager);

        // Set up TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPager);

        // Set up click listener for home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to navigate to DashUserActivity
                navigateToDashUser();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager()); // Use FragmentManager
        adapter.addFragment(new PendingFragment(), "Pending");
        adapter.addFragment(new AcceptedFragment(), "Accepted");
        viewPager.setAdapter(adapter);
    }

    // Function to navigate to DashUserActivity
    private void navigateToDashUser() {
        Intent intent = new Intent(UserDesignStatus.this, dash_user.class);  // Navigate to DashUserActivity
        startActivity(intent);
    }
}
