package com.oc.exomindgetweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;

    public Main() {
    }

    public Double getTemp() {
        return temp;
    }

}
