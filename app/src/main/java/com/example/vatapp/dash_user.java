package com.example.vatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ProfileImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dash_user extends AppCompatActivity {

    private TextSwitcher textSwitcher;
    private String[] texts = {
            "We Provide best designs .",
            "Can view it before actually getting it",
            "There is always a better way for your design",
            "Happy design, happy customer",
            "Check out our professional designers"
    };
    private int index = 0;
    private Handler handler = new Handler();

    private ImageView profileImageView;  // Add ImageView for profile image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);

        // Initialize TextSwitcher
        textSwitcher = findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(() -> {
            TextView textView = new TextView(getApplicationContext());
            textView.setTextSize(40);
            textView.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.nanum_brush_script));
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            return textView;
        });

        // Set fade animations
        textSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        textSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        // Start text switching
        startTextSwitcher();

        // Initialize TextViews for navigation
        TextView txtNewDesign = findViewById(R.id.txtNewDesign);
        TextView profile = findViewById(R.id.textView24);
        TextView designStatus = findViewById(R.id.textView21);
        TextView designersList = findViewById(R.id.designers_list);

        // Initialize profile image view
        profileImageView = findViewById(R.id.profile_img);

        // Load profile image using Retrofit
        loadProfileImage();

        // Set click listeners
        txtNewDesign.setOnClickListener(v -> startActivity(new Intent(dash_user.this, RequestNewDesign.class)));
        profile.setOnClickListener(v -> startActivity(new Intent(dash_user.this, UserProfile.class)));
        designStatus.setOnClickListener(v -> startActivity(new Intent(dash_user.this, UserDesignStatus.class)));
        designersList.setOnClickListener(v -> startActivity(new Intent(dash_user.this, designers_list.class)));
    }

    private void startTextSwitcher() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textSwitcher.setText(texts[index]);
                index = (index + 1) % texts.length; // Loop through text
                handler.postDelayed(this, 8000); // Change text every 8 seconds
            }
        };
        handler.post(runnable);
    }

    private void loadProfileImage() {
        // Get user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        if (userId != null) {
            // Use ApiService to call getProfileImage
            RetrofitClient.getApiService().getProfileImage(userId).enqueue(new Callback<ProfileImageResponse>() {
                @Override
                public void onResponse(Call<ProfileImageResponse> call, Response<ProfileImageResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Log the full response for debugging
                        Log.d("ProfileImage", "Response: " + response.body().toString());
                        String imageUrl = response.body().getImage_url();

                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            // Log for debugging
                            Log.d("ProfileImage", "Full Image URL: " + imageUrl);

                            // Load image using Glide
                            Glide.with(dash_user.this)
                                    .load(RetrofitClient.Image_base_url + imageUrl)
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.placeholder)
                                    .into(profileImageView);
                        } else {
                            Log.e("ProfileImage", "Image URL is empty or null.");
                        }
                    } else {
                        Log.e("ProfileImage", "Failed to fetch image. Response code: " + response.code());
                    }
                }


                @Override
                public void onFailure(Call<ProfileImageResponse> call, Throwable t) {
                    // Handle failure
                    Log.e("ProfileImage", "Error: " + t.getMessage());
                }
            });
        } else {
            Log.e("ProfileImage", "User ID is null.");
        }
    }

}
