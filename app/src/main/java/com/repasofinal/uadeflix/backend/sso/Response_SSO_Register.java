package com.repasofinal.uadeflix.backend.sso;

import com.google.gson.annotations.SerializedName;

public class Response_SSO_Register {
    @SerializedName("msg")
    private String msg;
    @SerializedName("error")
    private String error;

    public String getMsg() { return msg; }
    public String getError() { return error; }
}
