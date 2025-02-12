package com.example.vatapp;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.response.DesignStatusResponse;
import java.util.List;

public class DesignRequestedAdapter extends RecyclerView.Adapter<DesignRequestedAdapter.ViewHolder> {

    private List<DesignStatusResponse.Design> designList;
    private OnItemClickListener onItemClickListener;

    public DesignRequestedAdapter(List<DesignStatusResponse.Design> designList, OnItemClickListener onItemClickListener) {
        this.designList = designList != null ? designList : List.of(); // Ensures no null list
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView statusTextView, acceptedIdTextView, acceptedNameTextView;
        Button detailsButton;

        public ViewHolder(View itemView) {
            super(itemView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            acceptedIdTextView = itemView.findViewById(R.id.acceptedIdTextView);
            acceptedNameTextView = itemView.findViewById(R.id.acceptedNameTextView);
            detailsButton = itemView.findViewById(R.id.goToDetailsButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_requested_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DesignStatusResponse.Design design = designList.get(position);

        holder.statusTextView.setText("Status: " + (TextUtils.isEmpty(design.getStatus()) ? "N/A" : design.getStatus()));
        if (design.getAcceptedId() != null){
            holder.acceptedIdTextView.setText("Accepted ID: " + (design.getAcceptedId() > 0 ? design.getAcceptedId() : "N/A"));
            holder.acceptedNameTextView.setText("Accepted Name: " + (TextUtils.isEmpty(design.getAcceptedName()) ? "N/A" : design.getAcceptedName()));
        }

        holder.detailsButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(design);
            }
        });
    }

    @Override
    public int getItemCount() {
        return designList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(DesignStatusResponse.Design design);
    }
}
