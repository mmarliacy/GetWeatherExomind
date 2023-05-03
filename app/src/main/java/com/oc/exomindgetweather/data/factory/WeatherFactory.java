package com.oc.exomindgetweather.data.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.oc.exomindgetweather.data.repositories.CityRepository;
import com.oc.exomindgetweather.data.repositories.WeatherRepository;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

import org.jetbrains.annotations.NotNull;

public class WeatherFactory implements ViewModelProvider.Factory{

    // 1 -- REPOSITORY & EXECUTOR -->
    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    /** CONSTRUCTOR */
    public WeatherFactory(CityRepository pCityRepository, WeatherRepository pWeatherDataSource) {
        this.weatherRepository = new WeatherRepository();
        cityRepository = pCityRepository;
    }

    // 2 -- UNIQUE INSTANCE OF VIEW MODEL -->
    @NotNull
    public <T extends ViewModel> T create(Class<T>  modelClass){
        if(modelClass.isAssignableFrom(WeatherViewModel.class)){
            //noinspection unchecked
            return (T) new WeatherViewModel(weatherRepository, cityRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
