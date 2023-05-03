package com.oc.exomindgetweather.data.factory;

import android.content.Context;

import com.oc.exomindgetweather.data.database.CityDatabase;
import com.oc.exomindgetweather.data.repositories.CityRepository;
import com.oc.exomindgetweather.data.repositories.WeatherRepository;
import com.oc.exomindgetweather.data.webservice.ApiCall;

import java.util.concurrent.Executor;

public class Injection {

    public static CityRepository provideCityDataSource(Context context) {
        CityDatabase database = CityDatabase.getInstance(context);
        return new CityRepository(database.fCityDAO());
    }

    public static WeatherRepository provideWeatherDataSource(Context context){
        return new WeatherRepository();
    }

    //-- Returns ViewModelFactory's objects --
    public static WeatherFactory provideViewModelFactory(Context context) {
        CityRepository cityDataSource = provideCityDataSource(context);
        WeatherRepository weatherDataSource = provideWeatherDataSource(context);
        return new WeatherFactory(cityDataSource,weatherDataSource);
    }
}
