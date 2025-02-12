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
import com.example.vatapp.response.RequestAcceptedResponse;
import java.util.List;

public class AcceptedAdapter extends RecyclerView.Adapter<AcceptedAdapter.ViewHolder> {

    private List<RequestAcceptedResponse.Request> acceptedItems;
    private Context context;
    private OnDetailsClickListener onDetailsClickListener;

    public AcceptedAdapter(List<RequestAcceptedResponse.Request> acceptedItems, Context context) {
        this.acceptedItems = acceptedItems;
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
        RequestAcceptedResponse.Request item = acceptedItems.get(position);
        holder.idTextView.setText("ID: " + item.getRequester_id());
        holder.nameTextView.setText("Requester: " + item.getRequester_name());

        holder.detailsButton.setOnClickListener(v -> {
            if (onDetailsClickListener != null) {
                onDetailsClickListener.onDetailsClick(item);
            }

            // Start DesignDescription activity with data
            Intent intent = new Intent(context, DesignDescription.class);
            intent.putExtra("id", "" + item.getId());
            intent.putExtra("requester_name", "" + item.getRequester_name());
            intent.putExtra("description", "" + item.getDescription());
            intent.putExtra("image_url", "" + item.getSample_image());
            intent.putExtra("from_accepted", true); // Pass extra flag
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return acceptedItems != null ? acceptedItems.size() : 0;
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
        void onDetailsClick(RequestAcceptedResponse.Request item);
    }
}
