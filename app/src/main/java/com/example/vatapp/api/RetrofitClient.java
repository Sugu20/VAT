package com.example.vatapp.api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static final String Image_base_url = "https://5g56w5fj-80.inc1.devtunnels.ms/VAT_APP/";
    public static final String Base_url = "https://5g56w5fj-80.inc1.devtunnels.ms/";
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Create an instance of the logging interceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Build the OkHttpClient
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor) // Add logging interceptor
                    .connectTimeout(30, TimeUnit.SECONDS) // Connection timeout
                    .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
                    .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
                    .build();

            // Build the Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_url) // Replace with your API URL
                    .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter
                    .client(client) // Attach OkHttpClient
                    .build();
        }
        return retrofit;
    }
}


