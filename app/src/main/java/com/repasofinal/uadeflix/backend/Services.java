package com.repasofinal.uadeflix.backend;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Services {
    @POST("login")
    Call<Response_Login> Login(@Body Body_Login user);
    @POST("register")
    Call<Response_Register> Register(@Body Body_Register user);
    @GET("logout")
    Call<Response_Logout> Logout();
}
