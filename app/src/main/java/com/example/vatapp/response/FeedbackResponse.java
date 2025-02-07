package com.example.vatapp.response;

import java.util.List;

public class FeedbackResponse {
    private boolean success;
    private List<DesignersFeedback> feedbacks;

    public boolean isSuccess() {
        return success;
    }

    public List<DesignersFeedback> getFeedbacks() {
        return feedbacks;
    }

    public static class DesignersFeedback {

        private int id;
        private int user_id;
        private String feedback_text;
        private int image_id;
        private int designer_id;
        private String created_at;
        private String user_name;

        private String file_path;

        public int getId() {
            return id;
        }

        public int getUserId() {
            return user_id;
        }

        public String getFeedbackText() {
            return feedback_text;
        }

        public int getImage_id() {
            return image_id;
        }

        public int getDesigner_id() {
            return designer_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUser_name() {
            return user_name;
        }
        public String getFile_path(){
            return file_path;
        }
    }
}
