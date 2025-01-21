package com.example.vatapp.api;

import com.example.vatapp.response.DesignersResponse;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;
import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("VAT_APP/signup.php") // Replace with your actual PHP endpoint
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("VAT_APP/logIn.php") // Replace with the actual endpoint path
    Call<LoginResponse> loginUser(@Body LoginRequest request);


    @GET("designers_list.php") // Replace with the actual endpoint
    Call<DesignersResponse> getDesigners();
    }

