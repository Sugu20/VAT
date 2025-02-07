package com.example.vatapp; // replace with your app's package name

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ProfileImageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dash_designer extends AppCompatActivity {

    private TextView textdesignRequest, profileTextView, textDesignerspreviosupload, txtFeedback;
    private TextSwitcher textSwitcher;

    private ImageView profileImageView1;
    private String[] texts = {
            "We Provide best designs.",
            "Can view it before actually getting it.",
            "There is always a better way for your design.",
            "Happy design, happy customer.",
            "Check out our professional designers."
    };
    private int index = 0;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_designer); // replace with the correct layout file name

        // Initialize views
        textdesignRequest = findViewById(R.id.txtDesignRequestList);
        profileTextView = findViewById(R.id.txtProfile);
        textDesignerspreviosupload = findViewById(R.id.txtDesignersPreviousUpload);
        txtFeedback = findViewById(R.id.txtDesignsFeedback);
        textSwitcher = findViewById(R.id.textSwitcher);
        profileImageView1 = findViewById(R.id.profile_img);


        // Set up TextSwitcher
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(dash_designer.this);
                textView.setTextSize(40);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.nanum_brush_script));
                return textView;
            }
        });

        // Set animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        textSwitcher.setInAnimation(fadeIn);
        textSwitcher.setOutAnimation(fadeOut);

        // Start text switcher
        startTextSwitcher();

        // Set OnClickListener for "Upload Design"
        textdesignRequest.setOnClickListener(v -> {
            Intent intent = new Intent(dash_designer.this, DesignRequestList.class); // replace with actual class name for import page
            startActivity(intent);
        });

        // Set OnClickListener for "Profile"
        profileTextView.setOnClickListener(v -> {
            Intent intent = new Intent(dash_designer.this, profile_page.class); // replace with actual class name for profile page
            startActivity(intent);
        });

        // Set OnClickListener for "Design Requests"
        txtFeedback.setOnClickListener(v -> {
            Intent intent = new Intent(dash_designer.this, DesignsFeedback.class); // replace with actual class name for design requests page
            startActivity(intent);
        });

        // Set OnClickListener for "Designer's Previous Upload"
        textDesignerspreviosupload.setOnClickListener(v -> {
            Intent intent = new Intent(dash_designer.this, designers_previous.class); // replace with actual class name for previous uploads page
            startActivity(intent);
        });

        loadProfileImage();

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
                            Glide.with(dash_designer.this)
                                    .load(RetrofitClient.Image_base_url + imageUrl)
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.placeholder)
                                    .into((ImageView) profileImageView1);
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
}
