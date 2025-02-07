package com.example.vatapp;

import com.example.vatapp.api.RetrofitClient;

public class DesignersFeedback {
    private String user_name;
    private String feedbackText;
    private String filePath;

    public DesignersFeedback(String userName, String feedbackText, String filePath) {
        this.user_name = userName;
        this.feedbackText = feedbackText;
        this.filePath = filePath;

    }

    public String getUserName() {
        return user_name;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFullImageUrl() {
        return RetrofitClient.Image_base_url + filePath; // FIXED!
    }
}
