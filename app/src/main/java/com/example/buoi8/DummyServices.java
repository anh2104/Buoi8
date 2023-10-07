package com.example.buoi8;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DummyServices {

    @GET("products")
    Call<ProductModel> getProducts();

    @GET("products/{idProduct}")
    Call<Product> getProductById(@Path("idProduct") String idProduct);

    @Headers("Content-Type': 'application/json")
    @POST("Products/add")
    Call<JsonObject> addProduct(@Body AddProductRequest addProductRequest);



}
