package com.repasofinal.uadeflix.backend.suscriptions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Subscriptions_Services {
    @GET("paquetes")
    Call<Response_Subscriptions_Paquete[]> GetSubscriptions();
}
