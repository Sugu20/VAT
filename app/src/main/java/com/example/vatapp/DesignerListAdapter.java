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

import java.util.List;

public class DesignerListAdapter extends RecyclerView.Adapter<DesignerListAdapter.ViewHolder> {

    private List<Designer> designerList;
    private Context context;

    public DesignerListAdapter(List<Designer> designerList, Context context) {
        this.designerList = designerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.designer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Designer designer = designerList.get(position);
        holder.nameTextView.setText(designer.getName());
        holder.emailTextView.setText(designer.getEmail());

        // Navigate to profile_page on button click
        holder.goToDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserDesignerProfile.class);
            intent.putExtra("user_id", ""+designer.getUser_id());
            intent.putExtra("name", designer.getName());
            intent.putExtra("email", designer.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return designerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;
        Button goToDetailsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.titleTextView);
            emailTextView = itemView.findViewById(R.id.descriptionTextView);
            goToDetailsButton = itemView.findViewById(R.id.goToDetailsButton);
        }
    }
}
