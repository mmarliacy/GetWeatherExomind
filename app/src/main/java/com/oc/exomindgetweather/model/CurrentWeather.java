package com.oc.exomindgetweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrentWeather {

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("name")
    @Expose
    private String name;


    public CurrentWeather() {
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
