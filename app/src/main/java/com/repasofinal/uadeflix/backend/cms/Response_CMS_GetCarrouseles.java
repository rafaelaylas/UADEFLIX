package com.repasofinal.uadeflix.backend.cms;

import com.google.gson.annotations.SerializedName;
import com.repasofinal.uadeflix.logic.Catalog;

import java.util.ArrayList;

public class Response_CMS_GetCarrouseles {
    @SerializedName("results")
    private Response_CMS_Carrousel[] results;

    public Response_CMS_Carrousel[] getResults() { return results; }

    public ArrayList<Catalog> ToCatalogs()
    {
        ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
        for (Response_CMS_Carrousel content: results) { catalogs.add(content.ToCatalog()); }
        return catalogs;
    }
}
