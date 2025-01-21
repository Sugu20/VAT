package com.example.vatapp.response;


import com.example.vatapp.Designer;

import java.util.List;

public class DesignersResponse {
    private String status;
    private List<Designer> data;

    public String getStatus() {
        return status;
    }

    public List<Designer> getData() {
        return data;
    }
}
