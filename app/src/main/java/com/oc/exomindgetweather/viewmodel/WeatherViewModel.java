package com.oc.exomindgetweather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.oc.exomindgetweather.data.repositories.CityRepository;
import com.oc.exomindgetweather.data.repositories.WeatherRepository;
import com.oc.exomindgetweather.model.CityModel;
import com.oc.exomindgetweather.model.weather_info.CurrentWeather;

import java.util.List;

public class WeatherViewModel extends ViewModel {

    // 1 -- REPOSITORIES -->
    WeatherRepository fWeatherRepository;
    CityRepository fCityRepository;

    // 2 -- DATA -->
    private LiveData<List<CityModel>> allCities;
    private LiveData<CurrentWeather> cityWeather;


    /**
     * CONSTRUCTOR
     */
    public WeatherViewModel(WeatherRepository pWeatherRepository, CityRepository pCityRepository) {
        this.fWeatherRepository = pWeatherRepository;
        this.fCityRepository = pCityRepository;
    }

    // 3 -- GET LAST DATA -->
    public void initCitiesList() {
        if (this.allCities != null) {
            return;
        }
        allCities = fCityRepository.getCities();
    }


    // 4 -- GET WEATHER FOR CITY --


    private void getWeather(CityModel pCity){
        fWeatherRepository.getWeatherAnswer(pCity.getLat(), pCity.getLon(), "metric", new WeatherRepository.WeatherResponse() {
            @Override
            public void Response(CurrentWeather pCurrentWeather) {
            }

            @Override
            public void Failure(Throwable pThrowable) {

            }
        });
    }




}
