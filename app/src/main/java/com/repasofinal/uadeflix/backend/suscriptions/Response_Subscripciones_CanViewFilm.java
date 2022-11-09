package com.repasofinal.uadeflix.backend.suscriptions;

import com.google.gson.annotations.SerializedName;

public class Response_Subscripciones_CanViewFilm {
    @SerializedName("puede_ver")
    private Boolean puede_ver;

    public Boolean getPuede_ver() { return puede_ver; }
}
