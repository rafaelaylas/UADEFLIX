package com.repasofinal.uadeflix.backend.sso;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Body_SSO_Register {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("tenant")
    private String tenant;
    @SerializedName("admin")
    private String admin;

    public Body_SSO_Register(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.tenant = "Web_Mobile";
        this.admin = "false";
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTenant() { return tenant; }
    public String getAdmin() { return admin; }
}
