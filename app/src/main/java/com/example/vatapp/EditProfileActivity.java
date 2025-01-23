package com.example.vatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail;
    private Button buttonSave, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Get data passed from ProfilePage
        Intent intent = getIntent();
        String currentName = intent.getStringExtra("name");
        String currentEmail = intent.getStringExtra("email");

        // Set current data to EditTexts
        editTextName.setText(currentName);
        editTextEmail.setText(currentEmail);

        // Save button logic
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editTextName.getText().toString().trim();
                String newEmail = editTextEmail.getText().toString().trim();

                if (newName.isEmpty() || newEmail.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save data (could be sent to database or shared preferences)
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("updatedName", newName);
                    resultIntent.putExtra("updatedEmail", newEmail);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        // Cancel button logic
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simply finish the activity without saving
                finish();
            }
        });
    }
}
