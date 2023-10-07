package com.example.buoi8;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartServices {


    @GET("carts")
    Call<CartResponse> getCarts();

    @GET("carts/{idCart}")
    Call<Cart> getCartById(@Path("idCart") String idCart);

}
