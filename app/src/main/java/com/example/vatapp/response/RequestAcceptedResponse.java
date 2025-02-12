package com.example.vatapp.response;

import java.util.List;

public class RequestAcceptedResponse {
    private boolean success; // Change this from String to boolean
    private List<Request> requests;

    public boolean isSuccess() {
        return success;
    } // Ensure this method exists

    public List<Request> getRequests() {
        return requests;
    }

    public static class Request {
        private String id;
        private String requester_id;
        private String requester_name;
        private String sample_image;
        private String description;

        public String getId() {
            return id;
        }

        public String getRequester_id() {
            return requester_id;
        }

        public String getRequester_name() {
            return requester_name;
        }

        public String getSample_image() {
            return sample_image;
        }

        public String getDescription() {
            return description;
        }
    }
}
