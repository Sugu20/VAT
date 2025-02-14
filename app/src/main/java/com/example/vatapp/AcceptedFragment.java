package com.example.vatapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.RequestAcceptedResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptedFragment extends Fragment {

    private RecyclerView recyclerView;
    private AcceptedAdapter acceptedAdapter;
    private List<RequestAcceptedResponse.Request> acceptedItems;
    private static final String TAG = "AcceptedFragment";

    public AcceptedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accepted, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.Accepted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load accepted requests
        loadAcceptedRequests();

        return view;
    }

    private void loadAcceptedRequests() {
        // Get user_id from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        if (userId == null) {
            Toast.makeText(getContext(), "User ID not found", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "User ID not found in SharedPreferences");
            return;
        }

        Log.d(TAG, "Retrieved user_id: " + userId);

        // Retrofit API call
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RequestAcceptedResponse> call = apiService.getAcceptedRequests(Integer.parseInt(userId));

        call.enqueue(new Callback<RequestAcceptedResponse>() {
            @Override
            public void onResponse(Call<RequestAcceptedResponse> call, Response<RequestAcceptedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "API Response: " + response.body().toString());
                    acceptedItems = response.body().getRequests();
                    if (acceptedItems != null && !acceptedItems.isEmpty()) {
                        acceptedItems.clear();
                        acceptedAdapter = new AcceptedAdapter(acceptedItems, getContext());
                        recyclerView.setAdapter(acceptedAdapter);
                    } else {
                        Toast.makeText(getContext(), "No accepted requests found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Failed response: " + response.errorBody());
                    Toast.makeText(getContext(), "Failed to load accepted requests", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestAcceptedResponse> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAcceptedRequests();
    }
}


