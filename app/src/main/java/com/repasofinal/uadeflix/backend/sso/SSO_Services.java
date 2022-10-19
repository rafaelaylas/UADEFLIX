package com.repasofinal.uadeflix.backend.sso;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SSO_Services {
    @POST("login")
    Call<Response_SSO_Login> Login(@Body Body_SSO_Login user);
    @POST("register")
    Call<Response_SSO_Register> Register(@Body Body_SSO_Register user);
    @GET("logout")
    Call<Response_SSO_Logout> Logout();
}
