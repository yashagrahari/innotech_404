package com.example.abeshackathon.JsonBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WardData {

    @SerializedName("id")
    @Expose
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
