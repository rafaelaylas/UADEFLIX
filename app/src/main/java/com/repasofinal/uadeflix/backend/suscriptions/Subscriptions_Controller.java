package com.repasofinal.uadeflix.backend.suscriptions;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Subscriptions_Controller {
    public static final String BASE_URL = "http://intap-backoffice.herokuapp.com/public/api/";
    private static Retrofit client = null;

    public static Retrofit get_Client() {
        if (client == null) { client = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build(); }
        return client;
    }

    public static Call<Response_Subscriptions_Paquete[]> GetPaquetes() { return get_Client().create(Subscriptions_Services.class).GetSubscriptions(); }

}
