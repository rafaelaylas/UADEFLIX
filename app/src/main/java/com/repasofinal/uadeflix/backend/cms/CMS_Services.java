package com.repasofinal.uadeflix.backend.cms;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CMS_Services {
    @GET("carruseles")
    Call<Response_CMS_GetCarrouseles> GetCarrouseles();
}
