package com.example.vatapp.response;


import com.example.vatapp.DesignersFeedback;

import java.util.List;

public class FeedbackResponse {
    private boolean success;
    private List<DesignersFeedback> feedbacks;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public List<DesignersFeedback> getFeedbacks() {
        return feedbacks;
    }

    public String getMessage() {
        return message;
    }
}
