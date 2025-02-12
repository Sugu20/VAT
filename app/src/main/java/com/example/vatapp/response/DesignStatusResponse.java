package com.example.vatapp.response;

import java.util.List;

public class DesignStatusResponse {
    private boolean success;
    private List<Design> requests;

    public boolean isSuccess() {
        return success;
    }

    public List<Design> getRequests() {
        return requests;
    }

    public static class Design {
        private int id;
        private int requester_id;
        private String status;
        private Integer accepted_id; // Using Integer to handle null values
        private String sample_image;
        private String description;
        private String upload_image;
        private String accepted_name;

        public int getId() {
            return id;
        }

        public int getRequesterId() {
            return requester_id;
        }

        public String getStatus() {
            return status;
        }

        public Integer getAcceptedId() {
            return accepted_id;
        }

        public String getSampleImage() {
            return sample_image;
        }

        public String getDescription() {
            return description;
        }

        public String getUploadImage() {
            return upload_image;
        }

        public String getAcceptedName() {
            return accepted_name;
        }
    }
}
