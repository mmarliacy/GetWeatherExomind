package com.oc.exomindgetweather.data.webservice;

import com.oc.exomindgetweather.model.CurrentWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    // -- CALL :: WEATHER DATA -->
    @GET("data/2.5/weather?units=metric")
    Call<CurrentWeather> getWeatherForCity(@Query ("q") String city, @Query ("appid") String api );
}
