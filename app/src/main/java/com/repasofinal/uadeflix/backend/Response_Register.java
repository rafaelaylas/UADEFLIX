package com.repasofinal.uadeflix.backend;

import com.google.gson.annotations.SerializedName;

public class Response_Register {
    @SerializedName("msg")
    private String msg;
    @SerializedName("error")
    private String error;

    public String getMsg() { return msg; }
    public String getError() { return error; }
}
