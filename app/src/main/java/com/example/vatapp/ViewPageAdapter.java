package com.example.vatapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    // Constructor
    public ViewPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Get the fragment for the given position
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    // Get the number of fragments
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    // Add a fragment and its title
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    // Return the title of the fragment at the given position
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
