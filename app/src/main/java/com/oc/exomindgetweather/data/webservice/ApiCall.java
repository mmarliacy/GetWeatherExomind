package com.oc.exomindgetweather.data.webservice;

import com.oc.exomindgetweather.model.weather_info.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    //https://api.openweathermap.org/{lat}&lon={lon}&appid={API key}
    @GET
    Call<CurrentWeather> getWeatherForCity(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("units") String units,
            @Query("appid") String apiKey
    );
}
