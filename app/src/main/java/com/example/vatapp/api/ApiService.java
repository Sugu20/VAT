package com.example.vatapp.api;

import com.example.vatapp.response.AcceptedResponse;
import com.example.vatapp.response.DesignResponse;
import com.example.vatapp.response.DesignUploadResponse;
import com.example.vatapp.response.DesignersResponse;
import com.example.vatapp.response.FeedbackResponse;
import com.example.vatapp.response.ForgotPasswordRequest;
import com.example.vatapp.response.ForgotPasswordResponse;
import com.example.vatapp.response.LoginRequest;
import com.example.vatapp.response.LoginResponse;
import com.example.vatapp.response.ProfileData;
import com.example.vatapp.response.ProfileImageResponse;
import com.example.vatapp.response.RegisterRequest;
import com.example.vatapp.response.RegisterResponse;
import com.example.vatapp.response.RequestpendingResponse;
import com.example.vatapp.response.ResponseData;
import com.example.vatapp.response.UserDesignResponse;
import com.google.gson.JsonObject;

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
        @Multipart
        @POST("VAT_APP/signUp.php") // Replace with your actual endpoint
        Call<RegisterResponse> registerUserWithImage(
            @Part("name") RequestBody name,
            @Part("role") RequestBody role,
            @Part("email") RequestBody email,
            @Part("phone_number") RequestBody phoneNumber,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part profileImage
    );

        @GET("VAT_APP/get_profile_image.php")
        Call<ProfileImageResponse> getProfileImage(@Query("user_id") String userId);



    @POST("VAT_APP/logIn.php")
        // Ensure the endpoint matches your PHP script location
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("VAT_APP/fetch_designers_list.php")
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

    @GET("VAT_APP/fetch_designs.php")
        // Replace with your PHP script's relative path
    Call<DesignResponse> getDesigns(@Query("user_id") int userId);

    @Multipart
    @POST("VAT_APP/store_design_request.php")
    Call<ResponseBody> uploadDesign(
            @Part("user_id") RequestBody userId,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("VAT_APP/upload.php")
    Call<DesignUploadResponse> uploadImage(
            @Part MultipartBody.Part file,
            @Part("user_id") RequestBody userId
    );

    @Multipart
    @POST("VAT_APP/design_request.php") // Replace with your server's PHP script URL
    Call<ResponseData> submitDesignRequest(
            @Part("user_id") RequestBody userId,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part sampleImage
    );

    @GET("VAT_APP/request_list.php")
        // Replace with your actual API endpoint
    Call<RequestpendingResponse> getPendingRequests();

    @GET("VAT_APP/fetch_Designers_feedback.php")
        // Replace with your actual PHP file name
    Call<FeedbackResponse> getFeedback(
            @Query("designer_id") int designerId
    );
    @GET("VAT_APP/get_user_requests.php")
    Call<JsonObject> getUserRequests(@Query("user_id") String userId, @Query("details") String details);

    @FormUrlEncoded
    @POST("VAT_APP/Accepted.php") // Replace with actual PHP file path
    Call<AcceptedResponse> updateRequestStatus(
            @Field("id") int requestId,
            @Field("accepted_id") int acceptedId,
            @Field("status") String status
    );
}




