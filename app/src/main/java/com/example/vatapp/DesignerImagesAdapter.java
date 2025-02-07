package com.example.vatapp;

import static com.example.vatapp.api.RetrofitClient.Base_url;
import static com.example.vatapp.api.RetrofitClient.Image_base_url;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DesignerImagesAdapter extends RecyclerView.Adapter<DesignerImagesAdapter.ViewHolder> {

    private List<Integer> imageList; // List of image resource IDs
    private Context context;

    public DesignerImagesAdapter(List<Integer> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = String.valueOf(imageList.get(position));
        String url = Image_base_url+imageUrl;
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.placeholder) // Add a placeholder drawable
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView); // Ensure it matches the layout ID
        }
    }
}

