package com.example.vatapp.api;

import com.example.vatapp.response.DesignersResponse;
import com.example.vatapp.response.ForgotPasswordRequest;
import com.example.vatapp.response.ForgotPasswordResponse;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;
import com.example.vatapp.response.ProfileData;
import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("VAT_APP/signUp.php") // Replace with your actual PHP endpoint
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("VAT_APP/logIn.php")
    Call<LoginResponse> loginUser(@Body LoginRequest request);

    @GET("VAT_APP/designers_list.php")
    Call<DesignersResponse> getDesigners();

    @POST("VAT_APP/updatePassword.php")
    Call<ForgotPasswordResponse> resetPassword(@Body ForgotPasswordRequest request);
    // Fetch user data
    @GET("fetch_user.php")
    Call<ProfileData> fetchUserData(@Query("userId") String userId);

    // Update user profile
    @POST("update_user.php")
    @FormUrlEncoded
    Call<ResponseBody> updateUserProfile(
            @Field("userId") String userId,
            @Field("name") String name,
            @Field("email") String email
    );
}



