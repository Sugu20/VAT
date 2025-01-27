package com.example.vatapp.response;

import okhttp3.MultipartBody;

public class UploadRequest {
    private String user_id;
    private String description;
    private MultipartBody.Part image;

    public UploadRequest(String user_id, String description, MultipartBody.Part image) {
        this.user_id = user_id;
        this.description = description;
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }
}
