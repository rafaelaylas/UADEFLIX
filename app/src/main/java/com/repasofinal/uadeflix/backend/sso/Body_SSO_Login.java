package com.repasofinal.uadeflix.backend.sso;

import com.google.gson.annotations.SerializedName;

public class Body_SSO_Login {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("tenant")
    private String tenant = "Web_Mobile";

    public Body_SSO_Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTenant() { return tenant; }
}
