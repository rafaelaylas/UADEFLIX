package com.repasofinal.uadeflix.backend.suscriptions;

import com.repasofinal.uadeflix.logic.Movie;
import com.repasofinal.uadeflix.logic.Subscription;
import com.repasofinal.uadeflix.logic.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Subscriptions_Controller {
    public static final String BACK_BASE_URL = "http://intap-backoffice.herokuapp.com/public/api/";
    public static final String SUS_BASE_URL = "http://intap-suscripciones.herokuapp.com/public/api/";
    private static Retrofit back_client = null;
    private static Retrofit sus_client = null;

    public static Retrofit get_Back_Client() {
        if (back_client == null) { back_client = new Retrofit.Builder().baseUrl(BACK_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build(); }
        return back_client;
    }
    public static Retrofit get_Sus_Client() {
        if (sus_client == null) { sus_client = new Retrofit.Builder().baseUrl(SUS_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build(); }
        return sus_client;
    }

    public static Call<Response_Subscriptions_Paquete[]> GetPaquetes() { return get_Back_Client().create(Subscriptions_Services.class).GetSubscriptions(); }
    public static Call<Response_Subscripciones_CanViewFilm> CanViewFilm(User user, Movie movie) { return get_Sus_Client().create(Subscriptions_Services.class).CanViewFilm(user.getToken(), movie.getId()); }
    public static Call<String> Subscribe(User user, int[] subscriptions) { return get_Sus_Client().create(Subscriptions_Services.class).Subscribe(user.getToken(), new Body_Subscripciones_Subscribe(subscriptions)); }
}
