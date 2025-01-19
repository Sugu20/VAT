package com.example.vatapp.api;

import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("VAT_APP/signup.php") // Replace with your actual PHP endpoint
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);



}
