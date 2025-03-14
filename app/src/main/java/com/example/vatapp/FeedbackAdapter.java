package com.example.vatapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<Feedback> feedbackList;

    public FeedbackAdapter(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        holder.userNameTextView.setText(feedback.getUserName());
        holder.feedbackTextView.setText(feedback.getFeedbackText());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public void updateData(List<Feedback> newFeedbackList) {
        this.feedbackList = newFeedbackList;
        notifyDataSetChanged();
    }

    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, feedbackTextView;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
        }
    }
}
