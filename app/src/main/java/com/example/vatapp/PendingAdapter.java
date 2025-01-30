package com.example.vatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vatapp.response.RequestpendingResponse;
import java.util.List;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {

    private List<RequestpendingResponse.Request> pendingItems;
    private Context context;
    private OnDetailsClickListener onDetailsClickListener;

    public PendingAdapter(List<RequestpendingResponse.Request> pendingItems, Context context, OnDetailsClickListener onDetailsClickListener) {
        this.pendingItems = pendingItems;
        this.context = context;
        this.onDetailsClickListener = onDetailsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestpendingResponse.Request item = pendingItems.get(position);
        holder.idTextView.setText(item.getRequester_id());
        holder.nameTextView.setText(item.getRequester_name());

        holder.detailsButton.setOnClickListener(v -> {
            if (onDetailsClickListener != null) {
                onDetailsClickListener.onDetailsClick(item);
            }

            // Start DesignDescription activity with data
            Intent intent = new Intent(context, DesignDescription.class);
            intent.putExtra("requester_name", item.getRequester_name());
            intent.putExtra("description", item.getDescription()); // Ensure this is in Request class
            intent.putExtra("image_url", item.getSample_image());  // Ensure this is in Request class
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pendingItems != null ? pendingItems.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView;
        Button detailsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idtextview);
            nameTextView = itemView.findViewById(R.id.name);
            detailsButton = itemView.findViewById(R.id.goToDetailsButton);
        }
    }

    public interface OnDetailsClickListener {
        void onDetailsClick(RequestpendingResponse.Request item);
    }
}
