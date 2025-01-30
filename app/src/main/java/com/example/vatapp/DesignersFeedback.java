package com.example.vatapp;

import com.example.vatapp.api.RetrofitClient;

public class DesignersFeedback {
    private String userName;
    private String feedbackText;
    private String filePath; // Use 'filePath' instead of 'imageUrl'

    public DesignersFeedback(String userName, String feedbackText, String filePath) {
        this.userName = userName;
        this.feedbackText = feedbackText;
        this.filePath = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFullImageUrl() {
        return RetrofitClient.Image_base_url + filePath; // Construct full image URL
    }
}
