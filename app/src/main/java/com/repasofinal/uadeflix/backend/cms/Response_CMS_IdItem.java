package com.repasofinal.uadeflix.backend.cms;

import com.google.gson.annotations.SerializedName;

public class Response_CMS_IdItem {
    @SerializedName("id")
    private Integer id;
    @SerializedName("description")
    private String description;

    public Integer getId() { return id; }
    public String getDescription() { return description; }
}
