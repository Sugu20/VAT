package com.example.vatapp;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class UserStatus extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_status);

        TabLayout tabLayout = findViewById(R.id.tab_layout2); // Ensure the ID matches your XML
        ViewPager viewPager = findViewById(R.id.view_pager2); // Ensure the ID matches your XML

        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(getSupportFragmentManager());


        adapter.addFragment(new PendingFragment2(), "Pending");
        adapter.addFragment(new AcceptedFragment2(), "Accepted");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

