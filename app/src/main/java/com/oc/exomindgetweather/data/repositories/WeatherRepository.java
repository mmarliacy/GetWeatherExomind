package com.oc.exomindgetweather.data.repositories;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.oc.exomindgetweather.BuildConfig;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.webservice.ApiCall;
import com.oc.exomindgetweather.data.webservice.RetrofitClient;
import com.oc.exomindgetweather.model.CurrentWeather;
import com.oc.exomindgetweather.view.MainDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    //-----------
    // VARIABLES
    //-----------
    public static MutableLiveData<List<CurrentWeather>> newWeatherList = new MutableLiveData<>();
    private List<CurrentWeather> simpleList;
    double counter = 0.0;
    double step = 17;
    int indices = 0;

    // -- QUERY :: GET CITY WEATHER LIST -->
    public LiveData<List<CurrentWeather>> getList() {
        return newWeatherList;
    }

    // -- FUNCTION :: START PROGRESS BAR AND HANDLE ACTIONS -->
    public void turnOnProgressBar(ProgressBar pProgressBar, TextView percentIndicator, FragmentActivity pActivity, ViewGroup mainView, Fragment pFragment, int fragmentContainer) {
        simpleList = new ArrayList<>();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                pActivity.runOnUiThread(() -> {
                    counter++;
                    pProgressBar.setProgress((int) counter);
                    setPercentIndicator(pActivity, percentIndicator);
                    getCityList(pActivity);
                    if (counter == 100) {
                        updateProgressBar(pActivity, mainView);
                        launchFragment(pActivity, pFragment, fragmentContainer);
                        timer.cancel();
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 600);
    }

    // -- FUNCTION :: Set percent indicator -->
    @SuppressLint("SetTextI18n")
    public void setPercentIndicator(Activity pActivity, TextView percentIndicator) {
        pActivity.runOnUiThread(() -> percentIndicator.setText(counter + " %"));
    }

    // -- FUNCTION :: Get weather data according to city -->
    public void getCityList(FragmentActivity pActivity) {
        String[] cities = pActivity.getResources().getStringArray(R.array.city_list);
        String api_key = BuildConfig.ApiKey;
        while (counter == step && indices < 5) {
            step += 17;
            getWeatherForCity(cities[indices], api_key);
            indices++;
        }
    }

    // -- QUERY :: GET WEATHER FOR CITY  -->
    public void getWeatherForCity(String city, String api_key) {
        ApiCall apiCall = RetrofitClient.getRetrofitClient().create(ApiCall.class);
        Call<CurrentWeather> weatherCall = apiCall.getWeatherForCity(city, api_key);
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    CurrentWeather currentWeather = response.body();
                    simpleList.add(currentWeather);
                    newWeatherList.postValue(simpleList);
                    Log.i("Size", simpleList.size() + "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.e("Request failed : ", t.getMessage());
            }
        });
    }

    // FUNCTION :: EXECUTE DISPLAYING MESSAGES -->
    public void setMessages(TextView indicator, Activity pActivity, String[] messages) {
        Thread t = new Thread() {
            public void run() {
                repeatMessages(indicator, pActivity, messages);
            }
        };
        t.start();
    }

    // FUNCTION :: REPEAT MESSAGES -->
    public void repeatMessages(TextView indicator, Activity pActivity, String[] messages) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(String message : messages) {
                    try {
                        displayMessage(indicator, pActivity, message);
                        Thread.sleep(6000);
                    } catch (InterruptedException pE) {
                        pE.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(timerTask, 0, 18000);
    }

    // FUNCTION :: DISPLAY MESSAGES -->
    public void displayMessage(TextView indicator, Activity pActivity, String message) {
        pActivity.runOnUiThread(() -> indicator.setText(message));
    }

    // FUNCTION :: LAUNCH LIST FRAGMENT -->
    public void launchFragment(FragmentActivity pActivity, Fragment pFragment, int fragmentContainer) {
        FragmentManager fragmentManager = pActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(fragmentContainer, pFragment);
        transaction.commit();
    }

    // FUNCTION :: UPDATE PROGRESS BAR -->
    private void updateProgressBar(FragmentActivity pActivity, ViewGroup bottomContainer) {
        pActivity.runOnUiThread(() -> {
            //-- Remove view --
            View constraintLayout = bottomContainer.findViewById(R.id.bar_container);
            bottomContainer.removeView(constraintLayout);
            //-- Display restart view --
            View childView = LayoutInflater.from(pActivity.getApplicationContext()).inflate(R.layout.restart_view, bottomContainer, false);
            bottomContainer.addView(childView);
            //-- Handle restart button --
            Button restartBtn = childView.findViewById(R.id.restart_btn);
            restartBtn.setOnClickListener(pView -> pActivity.startActivity(new Intent(pActivity.getApplicationContext(), MainDisplay.class)));
        });
    }
}
