package com.example.vatapp.response;
import java.util.List;

public class RequestpendingResponse {

    private String status;
    private List<Request> requests;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    // Inner class for individual requests
    public static class Request {
        private String requester_id;
        private String requester_name;
        private String user_id;
        private String sample_image;
        private String description;

        // Getters and setters
        public String getRequester_id() {
            return requester_id;
        }

        public void setRequester_id(String requester_id) {
            this.requester_id = requester_id;
        }

        public String getRequester_name() {
            return requester_name;
        }

        public void setRequester_name(String requester_name) {
            this.requester_name = requester_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSample_image() {
            return sample_image;
        }

        public void setSample_image(String sample_image) {
            this.sample_image = sample_image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
