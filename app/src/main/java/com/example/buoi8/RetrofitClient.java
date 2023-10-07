package com.example.buoi8;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit mInstance;
    public static final String BASE_URL = "https://dummyjson.com/";
    public static final String BASE_URL_CART = "https://dummyjson.com/carts/";

    public static Retrofit getInstance(){
        if (mInstance == null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

           mInstance = new Retrofit.Builder()
                   .client(okHttpClient)
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();

            mInstance = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL_CART)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mInstance;
    }

    public  static <T> T create(Class<T> t){
        return getInstance().create(t);
    }

}
