package com.oc.exomindgetweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrentWeather {

    @SerializedName("weather")
    @Expose
    private final ArrayList<Weather> weather;

    @SerializedName("main")
    @Expose
    private final Main main;

    @SerializedName("name")
    @Expose
    private final String name;

    /** CONSTRUCTOR */
    public CurrentWeather(ArrayList<Weather> pWeather, Main pMain, String pName) {
        weather = pWeather;
        main = pMain;
        name = pName;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}
