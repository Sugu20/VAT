package com.example.vatapp.api;

import com.example.vatapp.response.DesignersResponse;
import com.example.vatapp.response.ForgotPasswordRequest;
import com.example.vatapp.response.ForgotPasswordResponse;
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
    @POST("VAT_APP/logIn.php")
    Call<LoginResponse> loginUser(@Body LoginRequest request);


    @GET("VAT_APP/designers_list.php")
    Call<DesignersResponse> getDesigners();

    @POST("VAT_APP/updatePassword.php")
    Call<ForgotPasswordResponse> resetPassword(@Body ForgotPasswordRequest request);
}


