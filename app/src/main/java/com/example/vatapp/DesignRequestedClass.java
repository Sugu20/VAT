package com.example.vatapp;

public class DesignRequestedClass {

    private String userId;
    private String name;
    private String status;
    private String sampleImage;
    private String description;
    private String requestStatus;

    // Constructor
    public DesignRequestedClass(String userId, String name, String status, String sampleImage, String description, String requestStatus) {
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.sampleImage = sampleImage;
        this.description = description;
        this.requestStatus = requestStatus;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSampleImage() {
        return sampleImage;
    }

    public String getDescription() {
        return description;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSampleImage(String sampleImage) {
        this.sampleImage = sampleImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
