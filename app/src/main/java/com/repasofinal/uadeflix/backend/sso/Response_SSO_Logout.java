package com.repasofinal.uadeflix.backend.sso;

import com.google.gson.annotations.SerializedName;

public class Response_SSO_Logout {
    @SerializedName("error")
    private String error;
    @SerializedName("ok")
    private String ok;

    public String getError() { return error; }
    public String getOk() { return ok; }
}
