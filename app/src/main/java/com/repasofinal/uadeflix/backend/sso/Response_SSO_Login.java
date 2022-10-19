package com.repasofinal.uadeflix.backend.sso;

import com.google.gson.annotations.SerializedName;

public class Response_SSO_Login {
    @SerializedName("token")
    private String token;
    @SerializedName("expiredIn")
    private String expiredIn;
    @SerializedName("error")
    private String error;

    public String getToken() { return token; }
    public String getExpiredIn() { return expiredIn; }
    public String getError() { return error; }
}
