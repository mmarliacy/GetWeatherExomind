package com.oc.exomindgetweather.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_table")
public class CityModel {

    @PrimaryKey
    @ColumnInfo(name = "id", index = true)
    private int id;
    private String cityName;
    private Double lat;
    private Double lon;

    @Ignore
    public CityModel(int pId, String pCityName, Double pLat, Double pLon) {
        id = pId;
        cityName = pCityName;
        lat = pLat;
        lon = pLon;
    }

    public static CityModel[] getCities() {
        return new CityModel[]{
                new CityModel(1, "Rennes", 48.117266, -1.6777926),
                new CityModel(2, "Paris", 48.856614, 2.3522219),
                new CityModel(3,"Nantes", 47.218371, -1.553621),
                new CityModel(4, "Bordeaux", 44.837789, -0.57918),
                new CityModel(5, "Lyon", 	45.764043, 4.835659)
        };


    }

    public CityModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String pCityName) {
        cityName = pCityName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double pLat) {
        lat = pLat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double pLon) {
        lon = pLon;
    }
}
