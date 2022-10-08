package com.repasofinal.uadeflix.backend;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    public static final String BASE_URL_SSO = "https://ssog2.herokuapp.com/auth/";
    private static Retrofit retrofit_SSO = null;

    public static Retrofit getSSO_Client() {
        if (retrofit_SSO == null) { retrofit_SSO = new Retrofit.Builder().baseUrl(BASE_URL_SSO).addConverterFactory(GsonConverterFactory.create()).build(); }
        return retrofit_SSO;
    }

    public static Call<Response_Login> SignIn(String email, String password) { return getSSO_Client().create(Services.class).Login(new Body_Login(email, password)); }
    public static Call<Response_Logout> SignOut() { return getSSO_Client().create(Services.class).Logout(); }
    public static Call<Response_Register> SignUp(String name, String lastName, String email, String password) { return getSSO_Client().create(Services.class).Register(new Body_Register(name, lastName, email, password)); }
}
