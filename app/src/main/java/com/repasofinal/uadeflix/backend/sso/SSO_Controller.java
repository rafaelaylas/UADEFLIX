package com.repasofinal.uadeflix.backend.sso;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SSO_Controller {
    public static final String BASE_URL_SSO = "https://ssog2.herokuapp.com/auth/";
    private static Retrofit retrofit_SSO = null;

    public static Retrofit getSSO_Client() {
        if (retrofit_SSO == null) { retrofit_SSO = new Retrofit.Builder().baseUrl(BASE_URL_SSO).addConverterFactory(GsonConverterFactory.create()).build(); }
        return retrofit_SSO;
    }

    public static Call<Response_SSO_Login> SignIn(String email, String password) { return getSSO_Client().create(SSO_Services.class).Login(new Body_SSO_Login(email, password)); }
    public static Call<Response_SSO_Logout> SignOut(String refreshToken) { return getSSO_Client().create(SSO_Services.class).Logout(refreshToken); }
    public static Call<Response_SSO_Login> RefreshUser(String refreshToken) { return getSSO_Client().create(SSO_Services.class).Refresh(refreshToken); }
    public static Call<Response_SSO_Register> SignUp(String name, String lastName, String email, String password) { return getSSO_Client().create(SSO_Services.class).Register(new Body_SSO_Register(name, lastName, email, password)); }
}
