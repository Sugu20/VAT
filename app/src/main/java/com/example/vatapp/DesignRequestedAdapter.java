package com.example.vatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DesignRequestedAdapter extends RecyclerView.Adapter<DesignRequestedAdapter.ViewHolder> {

    private List<DesignRequestedClass> designList;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public DesignRequestedAdapter(List<DesignRequestedClass> designList, OnItemClickListener onItemClickListener) {
        this.designList = designList;
        this.onItemClickListener = onItemClickListener;
    }

    // ViewHolder to hold the item views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userIdTextView, nameTextView, statusTextView;
        Button detailsButton;

        public ViewHolder(View itemView) {
            super(itemView);
            userIdTextView = itemView.findViewById(R.id.titleTextView);
            nameTextView = itemView.findViewById(R.id.descriptionTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            detailsButton = itemView.findViewById(R.id.goToDetailsButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_requested_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DesignRequestedClass design = designList.get(position);

        holder.userIdTextView.setText("User ID: " + design.getUserId());
        holder.nameTextView.setText("Name: " + design.getName());
        holder.statusTextView.setText("Status: " + design.getStatus());

        holder.detailsButton.setOnClickListener(v -> {
            onItemClickListener.onItemClick(design);
        });
    }

    @Override
    public int getItemCount() {
        return designList.size();
    }

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(DesignRequestedClass design);
    }
}
