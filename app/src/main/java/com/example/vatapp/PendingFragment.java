package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.RequestpendingResponse;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class PendingFragment extends Fragment {

    private RecyclerView recyclerView;
    private PendingAdapter pendingAdapter;
    private List<RequestpendingResponse.Request> pendingItems = new ArrayList<>();

    public PendingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        recyclerView = view.findViewById(R.id.Pending);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pendingAdapter = new PendingAdapter(pendingItems, getContext(), this::onDetailsClick);
        recyclerView.setAdapter(pendingAdapter);

        fetchPendingRequests(); // Fetch data from API

        return view;
    }

    private void fetchPendingRequests() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.getPendingRequests().enqueue(new Callback<RequestpendingResponse>() {
            @Override
            public void onResponse(Call<RequestpendingResponse> call, Response<RequestpendingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pendingItems.clear();
                    pendingItems.addAll(response.body().getRequests());
                    pendingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RequestpendingResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void onDetailsClick(RequestpendingResponse.Request item) {
        // Open DesignDescription with data
        Intent intent = new Intent(getContext(), DesignDescription.class);
        intent.putExtra("requester_name", item.getRequester_name());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("image_url", item.getSample_image());
        startActivity(intent);
    }
}
