package com.example.vatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DesignerFeedbackAdapter extends RecyclerView.Adapter<DesignerFeedbackAdapter.ViewHolder> {

    private Context context;
    private List<DesignersFeedback> feedbackList;

    public DesignerFeedbackAdapter(Context context, List<DesignersFeedback> feedbackList) {
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
        DesignersFeedback feedback = feedbackList.get(position);
        holder.textFeedback.setText(feedback.getFeedbackText());
        holder.textDesignerName.setText(feedback.getUserName());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFeedback, textDesignerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textFeedback = itemView.findViewById(R.id.feedbackTextView1);
            textDesignerName = itemView.findViewById(R.id.userNameTextView1);
        }
    }
}
