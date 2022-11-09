package com.repasofinal.uadeflix.backend.cms;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CMS_Controller {
    public static final String BASE_URL_CMS = "https://uadeflix-cms.up.railway.app/api/";
    private static Retrofit retrofit_CMS = null;

    public static Retrofit getCMS_Client() {
        if (retrofit_CMS == null) { retrofit_CMS = new Retrofit.Builder().baseUrl(BASE_URL_CMS).addConverterFactory(GsonConverterFactory.create()).build(); }
        return retrofit_CMS;
    }

    public static Call<Response_CMS_GetCarrouseles> GetCarrouseles() { return getCMS_Client().create(CMS_Services.class).GetCarrouseles(); }

}
