package com.repasofinal.uadeflix.backend;

import com.google.gson.annotations.SerializedName;

public class Response_Logout {
    @SerializedName("error")
    private String error;
    @SerializedName("ok")
    private String ok;

    public String getError() { return error; }
    public String getOk() { return ok; }
}
