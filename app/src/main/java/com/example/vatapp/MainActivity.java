package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView lightShadow;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure XML layout is named properly

        // Initialize views
        lightShadow = findViewById(R.id.LightShadow);

        // Set up gesture detector for swipe handling
        gestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) { // Horizontal swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                    return true;
                }
            }
            return false;
        }

        private void onSwipeRight() {
            // Optional: Handle right swipe if needed
        }

        private void onSwipeLeft() {
            showLightShadowAndNavigate();
        }
    }

    private void showLightShadowAndNavigate() {
        // Make LightShadow visible
        lightShadow.setVisibility(ImageView.VISIBLE);

        // Delay for a small interval before navigating
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, SignIn.class);
            startActivity(intent);
            finish(); // Close the current activity
        }, 1000); // 1-second delay
    }
}
