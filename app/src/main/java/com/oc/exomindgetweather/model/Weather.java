package com.oc.exomindgetweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    @Expose
    private final Integer id;

    @SerializedName("icon")
    @Expose
    private final String icon;

    /** CONSTRUCTOR */
    public Weather(Integer pId, String pIcon) {
        id = pId;
        icon = pIcon;
    }

    public Integer getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

}
