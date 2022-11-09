package com.repasofinal.uadeflix.backend.suscriptions;

import com.repasofinal.uadeflix.logic.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Subscriptions_Services {
    @GET("paquetes")
    Call<Response_Subscriptions_Paquete[]> GetSubscriptions();
    @GET("canViewFilm/{id}")
    Call<Response_Subscripciones_CanViewFilm> CanViewFilm(@Header("token") String token, @Path("id") int movieId);

    @POST("suscribirseMultiple")
    Call<String> Subscribe(@Header("token") String token, @Body Body_Subscripciones_Subscribe subscriptions);
}
