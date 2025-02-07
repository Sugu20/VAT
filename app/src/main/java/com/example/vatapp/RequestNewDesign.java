package com.example.vatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.vatapp.api.ApiService;
import com.example.vatapp.api.RetrofitClient;
import com.example.vatapp.response.ResponseData;

import java.io.File;
import java.io.IOException;
import com.example.vatapp.FileUtils; // Make sure this is the correct package
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestNewDesign extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageUploaded;
    private AppCompatButton uploadButton, sendButton;
    private EditText descriptionInput;
    private TextView textPrompt, textPrompt2, requestNewDesign;
    private ImageButton homeButton;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_new_design);

        imageUploaded = findViewById(R.id.imageuploded);
        uploadButton = findViewById(R.id.Uploadbutton);
        sendButton = findViewById(R.id.button11);
        descriptionInput = findViewById(R.id.editTextTextMultiLine2);
        textPrompt = findViewById(R.id.TextPrompt);
        textPrompt2 = findViewById(R.id.TextPrompt2);
        requestNewDesign = findViewById(R.id.text25);
        homeButton = findViewById(R.id.homebutton1);

        uploadButton.setOnClickListener(v -> openGallery());
        sendButton.setOnClickListener(v -> submitDesignRequest());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageUploaded.setImageBitmap(bitmap);
                imageUploaded.setVisibility(View.VISIBLE);
                uploadButton.setVisibility(View.GONE); // Hide upload button after image is selected
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void submitDesignRequest() {
        String description = descriptionInput.getText().toString().trim();

        if (description.isEmpty()) {
            Toast.makeText(this, "Please enter a description for the design.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve user_id from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userIdValue = sharedPreferences.getString("user_id", null);

        if (userIdValue == null) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), userIdValue);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);

        if (imageUri != null) {
            File imageFile = new File(FileUtils.getPathFromUri(this, imageUri));

            if (imageFile == null) {
                Toast.makeText(this, "Error processing image.", Toast.LENGTH_SHORT).show();
                return;
            }

            RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("sample_image", imageFile.getName(), imageRequestBody);

            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<ResponseData> call = apiService.submitDesignRequest(userId, descriptionBody, imagePart);

            call.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ResponseData responseData = response.body();

                        if ("success".equals(responseData.getStatus())) {
                            Toast.makeText(RequestNewDesign.this, "Design request submitted successfully!", Toast.LENGTH_SHORT).show();
                            textPrompt.setVisibility(View.VISIBLE);
                            textPrompt2.setVisibility(View.VISIBLE);
                            requestNewDesign.setVisibility(View.VISIBLE);
                            homeButton.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(RequestNewDesign.this, responseData.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RequestNewDesign.this, "Request failed. Try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    Toast.makeText(RequestNewDesign.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please select an image.", Toast.LENGTH_SHORT).show();
        }
    }


    // Method to get real path from URI
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }
}
