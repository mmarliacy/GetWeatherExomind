package com.oc.exomindgetweather.data.factory;

import com.oc.exomindgetweather.data.repositories.WeatherRepository;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static WeatherRepository provideWeatherDataSource(){
        return new WeatherRepository();
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    //-- Returns ViewModelFactory object -->
    public static WeatherFactory provideViewModelFactory() {
        WeatherRepository weatherDataSource = provideWeatherDataSource();
        Executor executor = provideExecutor();
        return new WeatherFactory(weatherDataSource, executor);
    }
}
