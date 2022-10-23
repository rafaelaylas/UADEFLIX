package com.repasofinal.uadeflix.backend.suscriptions;

import com.google.gson.annotations.SerializedName;
import com.repasofinal.uadeflix.logic.Subscription;

import java.util.Date;

public class Response_Subscriptions_Paquete {
    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio")
    private String precio;
    @SerializedName("created_at")
    private Date created_at;
    @SerializedName("updated_at")
    private Date updated_at;
    @SerializedName("deleted_at")
    private Date deleted_at;

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getPrecio() { return precio; }
    public Date getCreated_at() { return created_at; }
    public Date getUpdated_at() { return updated_at; }
    public Date getDeleted_at() { return deleted_at; }
    public Subscription ToSubscription() { return new Subscription(id, nombre, descripcion, precio); }
}
