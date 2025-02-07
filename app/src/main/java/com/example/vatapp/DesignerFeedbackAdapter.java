package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.response.FeedbackResponse;

import java.util.List;

public class DesignerFeedbackAdapter extends RecyclerView.Adapter<DesignerFeedbackAdapter.ViewHolder> {

    private Context context;
    private List<FeedbackResponse.DesignersFeedback> feedbackList;

    public DesignerFeedbackAdapter(Context context, List<FeedbackResponse.DesignersFeedback> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedbackResponse.DesignersFeedback feedback = feedbackList.get(position);

        holder.textFeedback.setText(feedback.getUser_name());
        holder.textDesignerName.setText(feedback.getFeedbackText());


        // Handle "Details" button click
        holder.detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedBackDescription.class);
            intent.putExtra("feedback_text", feedback.getFeedbackText());
            intent.putExtra("user_name", feedback.getUser_name());
            intent.putExtra("image_url", feedback.getFile_path()); // FIXED!
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFeedback, textDesignerName;
        Button detailsButton; // FIXED: Added missing reference

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textFeedback = itemView.findViewById(R.id.titleTextView);
            textDesignerName = itemView.findViewById(R.id.descriptionTextView);
            detailsButton = itemView.findViewById(R.id.goToDetailsButton); // FIXED!
        }
    }
}
