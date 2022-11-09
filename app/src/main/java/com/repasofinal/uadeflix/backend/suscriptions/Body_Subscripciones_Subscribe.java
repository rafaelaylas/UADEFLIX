package com.repasofinal.uadeflix.backend.suscriptions;

import com.google.gson.annotations.SerializedName;

public class Body_Subscripciones_Subscribe {
    @SerializedName("paquetes_ids")
    private int[] paquetes_ids;

    public Body_Subscripciones_Subscribe(int[] paquetes_ids) { this.paquetes_ids = paquetes_ids; }

    public int[] getPaquetes_ids() { return paquetes_ids; }
}
