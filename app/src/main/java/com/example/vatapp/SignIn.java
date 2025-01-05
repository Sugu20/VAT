package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class SignIn extends AppCompatActivity {

    private Button signInButton;
    private TextView signUpNowTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_1);


        signInButton = findViewById(R.id.button2);


        signUpNowTextView = findViewById(R.id.textView9);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSignIn1();
            }
        });


        signUpNowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSignUp();
            }
        });
    }

    /**
     * Navigates to the SignIn1 activity.
     */
    private void navigateToSignIn1() {
        Intent intent = new Intent(SignIn.this, SignIn1.class);
        startActivity(intent);
    }

    /**
     * Navigates to the SignUp activity.
     */
    private void navigateToSignUp() {
        Intent intent = new Intent(SignIn.this, signup.class);
        startActivity(intent);
    }
}
