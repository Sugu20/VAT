package com.example.vatapp.api;

import com.example.vatapp.response.DesignResponse;
import com.example.vatapp.response.DesignUploadResponse;
import com.example.vatapp.response.DesignersResponse;
import com.example.vatapp.response.ForgotPasswordRequest;
import com.example.vatapp.response.ForgotPasswordResponse;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;
import com.example.vatapp.response.ProfileData;
import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("VAT_APP/signUp.php") // Replace with your actual PHP endpoint
    Call<RegisterResponse> registerUser(@Body RegisterRequest request);


    @POST("VAT_APP/logIn.php") // Ensure the endpoint matches your PHP script location
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("VAT_APP/designers_list.php")
    Call<DesignersResponse> getDesigners();

    @POST("VAT_APP/updatePassword.php")
    Call<ForgotPasswordResponse> resetPassword(@Body ForgotPasswordRequest request);
    // Fetch user data
    @GET("VAT_APP/fetch_user.php")
    Call<ProfileData> fetchUserData(@Query("userId") String userId);

    // Update user profile
    @POST("VAT_APP/update_user.php")
    @FormUrlEncoded
    Call<ResponseBody> updateUserProfile(
            @Field("userId") String userId,
            @Field("name") String name,
            @Field("email") String email
    );
    @GET("VAT_APP/fetch_designs.php") // Replace with your PHP script's relative path
    Call<DesignResponse> getDesigns(@Query("user_id") int userId);

    @Multipart
    @POST("VAT_APP/store_design_request.php")
    Call<ResponseBody> uploadDesign(
            @Part("user_id") RequestBody userId,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part image
    );
    @Multipart
    @POST("upload.php")
    Call<DesignUploadResponse> uploadImage(
            @Part MultipartBody.Part file,
            @Part("user_id") RequestBody userId
    );
    @Multipart
    @POST("design_request.php")
    Call<ResponseBody> requestNewDesign(
            @Part("user_id") RequestBody userId,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part sample_image
    );


}



