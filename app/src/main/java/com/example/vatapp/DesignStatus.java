package com.example.vatapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DesignStatus extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_status);

        TabLayout tabLayout = findViewById(R.id.tab_layout2); // Ensure the ID matches your XML
        ViewPager2 viewPager = findViewById(R.id.view_pager2); // Ensure the ID matches your XML

        // Initialize ViewPagerAdapter2
        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(getSupportFragmentManager(), getLifecycle());


        // Add fragments
        adapter.addFragment(new PendingFragment2(), "Pending");
        adapter.addFragment(new AcceptedFragment2(), "Accepted");

        // Set the adapter to the ViewPager2
        viewPager.setAdapter(adapter);

        // Link TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (int, position) -> {
            tab.setText(adapter.getFragmentTitle( position));
        }).attach();
    }
}

