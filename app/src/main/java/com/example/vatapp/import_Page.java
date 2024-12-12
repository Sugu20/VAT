package com.example.vatapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class import_Page extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImport = findViewById(R.id.button_import);

        btnImport.setOnClickListener(view -> showPopup());
    }

    private void showPopup() {
        // Inflate popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_import, findViewById(R.id.main), false);


        // Create PopupWindow
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                true
        );

        // Show the popup at the center of the main layout
        popupWindow.showAtLocation(findViewById(R.id.main), 0, 0, 0);

        // Initialize buttons in popup
        Button btnGallery = popupView.findViewById(R.id.btn_gallery);
        Button btnFiles = popupView.findViewById(R.id.btn_files);

        // Handle gallery import
        btnGallery.setOnClickListener(view -> {
            openGallery();
            popupWindow.dismiss();
        });

        // Handle file import
        btnFiles.setOnClickListener(view -> {
            openFileManager();
            popupWindow.dismiss();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    private void openFileManager() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedUri = data.getData();
            if (requestCode == 100) {
                Toast.makeText(this, "Selected from Gallery: " + selectedUri, Toast.LENGTH_SHORT).show();
            } else if (requestCode == 101) {
                Toast.makeText(this, "Selected from Files: " + selectedUri, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
