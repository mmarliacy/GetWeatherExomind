package com.oc.exomindgetweather.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oc.exomindgetweather.data.repositories.WeatherRepository;
import com.oc.exomindgetweather.model.CurrentWeather;

import java.util.List;
import java.util.concurrent.Executor;

public class WeatherViewModel extends ViewModel {

    // 1 -- REPOSITORY -->
    private final WeatherRepository fWeatherRepository;
    private final Executor executor;

    // 2 -- DATA -->
    private LiveData<List<CurrentWeather>> cityWeatherList;


    /**
     * CONSTRUCTOR
     */
    public WeatherViewModel(WeatherRepository pWeatherRepository, Executor pExecutor) {
        this.fWeatherRepository = pWeatherRepository;
        this.executor = pExecutor;
    }

    // 3 -- GET LAST DATA -->
    public void initCitiesList() {
        if (this.cityWeatherList != null) {
            return;
        }
        cityWeatherList = fWeatherRepository.getList();
    }

    // 4 -- GET WEATHER FOR CITY -->
    public LiveData<List<CurrentWeather>> getFinalList(){
        return this.cityWeatherList;
    }

    // 5 -- CONFIGURE MESSAGE ROTATION -->
    public void makeMessageRotation(TextView indicator, Activity pActivity, String[] messages){
        executor.execute(() -> fWeatherRepository.setMessages(indicator, pActivity, messages));
    }

    // 6 -- GET PROGRESS BAR -->
    public void makeProgressBarOn(ProgressBar pProgressBar, TextView percentIndicator, FragmentActivity pActivity, View loadingView, Fragment pFragment, int fragContainer,ConstraintLayout progressContainer, ConstraintLayout mainDisplayView, ConstraintLayout restartContainer){
        executor.execute(() -> fWeatherRepository.turnOnProgressBar(pProgressBar, percentIndicator, pActivity, loadingView, pFragment, fragContainer,progressContainer, mainDisplayView, restartContainer));
    }


}
