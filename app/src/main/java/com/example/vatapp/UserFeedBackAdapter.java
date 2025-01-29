package com.example.vatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vatapp.R;
import com.example.vatapp.response.UserFeedbackResponse;

import java.util.List;

public class UserFeedBackAdapter extends RecyclerView.Adapter<UserFeedBackAdapter.FeedbackViewHolder> {

    private List<Feedback> feedbackList;
    private Context context;

    public UserFeedBackAdapter(List<Feedback> feedbackList, Context context) {
        this.feedbackList = feedbackList;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        Feedback feedback= feedbackList.get(position);

        // Bind data to the views
        holder.userNameTextView.setText("User Name: " + feedback.getUserName());
        holder.feedbackTextView.setText(feedback.getFeedbackText());

        // Set button click listener
        holder.detailsButton.setOnClickListener(v -> {
            Toast.makeText(context, "Feedback ID: " + feedback.getId(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    // ViewHolder class
    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, feedbackTextView;
        RelativeLayout detailsButton;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
            detailsButton = itemView.findViewById(R.id.detailsButton);
        }
    }


}
