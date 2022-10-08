package com.repasofinal.uadeflix.backend;

import com.google.gson.annotations.SerializedName;

public class Body_Login {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("tenant")
    private String tenant = "Web_Mobile";

    public Body_Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTenant() { return tenant; }
}
