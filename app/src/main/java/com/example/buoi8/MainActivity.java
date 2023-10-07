package com.example.buoi8;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://dummyjson.com/";
    public static final String BASE_URL_CART = "https://dummyjson.com/carts/";
    private Retrofit mRetrofit;

    private  DummyServices dummyServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dummyServices = mRetrofit.create(DummyServices.class);
        dummyServices.getProducts().enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        ProductModel productModel = response.body();
                        Log.d(TAG, "onResponse: " + productModel.getProducts().get(0).getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        dummyServices.getProductById("2").enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        Product product = response.body();
                        Log.d(TAG, "onResponse: getProductById " + product.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

        DummyServices dummyServices2 = RetrofitClient.create(DummyServices.class);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_CART)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CartServices cartServices = RetrofitClient.create(CartServices.class);
        cartServices.getCarts().enqueue(new Callback<CartResponse>() {

            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200){
                        CartResponse cartResponse = response.body();
                        Log.d(TAG, "onResponse: " + cartResponse.getCarts().get(0).getTitle());
                    }
                }

            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        cartServices.getCartById("1").enqueue(new Callback<Cart>() {


            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Cart cart = response.body();
                        Log.d(TAG, "onResponse: getCartById " + cart.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
        dummyServices.addProduct(new AddProductRequest("Cái cốc")).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }
}