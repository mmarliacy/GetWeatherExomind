package com.oc.exomindgetweather.data.repositories;

import com.oc.exomindgetweather.data.webservice.ApiCall;
import com.oc.exomindgetweather.data.webservice.RetrofitClient;
import com.oc.exomindgetweather.model.weather_info.CurrentWeather;
import com.oc.exomindgetweather.view.HomeMenu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private final String api_key = HomeMenu.api_key;

    /**
     * CONSTRUCTOR
     */
    public WeatherRepository() {
    }

    // 1 -- QUERY : -->
    //-----------------------
    // -- QUERY :: GET CITY WEATHER -->
    public void getWeatherAnswer(Double latitude, Double longitude, String units, WeatherResponse weatherResponse) {
        ApiCall apiCall = RetrofitClient.getRetrofitClient().create(ApiCall.class);
        Call<CurrentWeather> weatherCall = apiCall.getWeatherForCity(latitude, longitude, units, api_key);
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    weatherResponse.Response(response.body());
                }
            }
            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                weatherResponse.Failure(new Throwable(t.getMessage()));
            }
        });
    }

    public interface WeatherResponse {
        void Response(CurrentWeather pCurrentWeather);
        void Failure(Throwable pThrowable);
    }
}
