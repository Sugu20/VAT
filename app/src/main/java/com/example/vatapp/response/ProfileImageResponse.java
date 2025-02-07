package com.example.vatapp.response;

public class ProfileImageResponse {
    
    private String status;
    private String image_url; // or whatever field corresponds to the profile image URL

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
