package com.example.vatapp.response;
import java.util.List;

import java.util.List;

public class RequestpendingResponse {
    private String status;
    private List<Request> requests;

    public String getStatus() {
        return status;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public static class Request {
        private String requester_id;
        private String requester_name;
        private String sample_image;
        private String description;

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
