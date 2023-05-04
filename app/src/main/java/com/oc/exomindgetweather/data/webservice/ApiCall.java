package com.oc.exomindgetweather.data.webservice;

import com.oc.exomindgetweather.model.CurrentWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    //https://api.openweathermap.org/{lat}&lon={lon}&appid={API key}
    @GET("data/2.5/weather?")
    Call<CurrentWeather> getWeatherForCity(@Query ("q") String city, @Query ("appid") String api );
}
