package com.repasofinal.uadeflix.backend.suscriptions;

import com.repasofinal.uadeflix.logic.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Subscriptions_Services {
    @GET("paquetes")
    Call<Response_Subscriptions_Paquete[]> GetSubscriptions();
    @GET("canViewFilm/{id}")
    Call<Boolean> CanViewFilm(@Header("token") String token, @Path("id") int movieId);

    @POST("suscribirse/{id}")
    Call<String> Subscribe(@Header("token") String token, @Path("id") int subscriptionId);
    @POST("desuscribirse/{id}")
    Call<String> Unsubscribe(@Header("token") String token, @Path("id") int subscriptionId);
}
