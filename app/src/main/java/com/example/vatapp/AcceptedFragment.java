package com.example.vatapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcceptedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcceptedFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private AcceptedAdapter acceptedAdapter;
    private List<String> acceptedItems; // Placeholder for your actual data type

    public AcceptedFragment() {
        // Required empty public constructor
    }

    public static AcceptedFragment newInstance(String param1, String param2) {
        AcceptedFragment fragment = new AcceptedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accepted, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.Accepted); // Ensure RecyclerView has an ID in XML
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data and adapter
        acceptedItems = new ArrayList<>();
        loadAcceptedItems(); // Load the data
        acceptedAdapter = new AcceptedAdapter(acceptedItems);
        recyclerView.setAdapter(acceptedAdapter);

        return view;
    }

    private void loadAcceptedItems() {
        // Load or fetch data for the accepted items list
        acceptedItems.add("Accepted Item 1");
        acceptedItems.add("Accepted Item 2");
        acceptedItems.add("Accepted Item 3");
        // Add more items or fetch from database/API
    }
}
