package com.oc.exomindgetweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private final Double temp;

    /** CONSTRUCTOR */
    public Main(Double pTemp) {
        temp = pTemp;
    }

    public Double getTemp() {
        return temp;
    }
}
