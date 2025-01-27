
package com.example.vatapp;
public class Feedback {
    private String userName;
    private String feedbackText;

    // Constructor
    public Feedback(String userName, String feedbackText) {
        this.userName = userName;
        this.feedbackText = feedbackText;
    }

    // Getter methods
    public String getUserName() {
        return userName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }
}
