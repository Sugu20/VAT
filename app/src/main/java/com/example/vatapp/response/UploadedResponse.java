package com.example.vatapp.response;

public class UploadedResponse {
    private boolean success;
    private String message;
    private String image_path;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getImagePath() {
        return image_path;
    }
}
