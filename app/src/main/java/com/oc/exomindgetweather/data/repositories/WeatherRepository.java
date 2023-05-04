package com.oc.exomindgetweather.data.repositories;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.webservice.ApiCall;
import com.oc.exomindgetweather.data.webservice.RetrofitClient;
import com.oc.exomindgetweather.model.CurrentWeather;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {


    public MutableLiveData<List<CurrentWeather>> newWeatherList;
    private final List<CurrentWeather> simpleList =  new ArrayList<>();
    double counter = 0.0;
    double step = 17;
    int indices = 0;

    /**
     * CONSTRUCTOR
     */
    public WeatherRepository() {
        newWeatherList = new MutableLiveData<>();
    }

    // 1 -- QUERY : -->
    //-----------------------
    // -- QUERY :: GET CITY WEATHER -->
    public void getWeatherAnswer(String city, String api_key) {
        ApiCall apiCall = RetrofitClient.getRetrofitClient().create(ApiCall.class);
        Call<CurrentWeather> weatherCall = apiCall.getWeatherForCity(city, api_key);
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    CurrentWeather currentWeather = response.body();
                    simpleList.add(currentWeather);
                    Log.i("Size", simpleList.size() + "");
                    Log.i("New", currentWeather.getName() + " est nouveau");
                    getList();
                }
            }
            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.e("Request failed", t.getMessage());
            }
        });
    }
    // -- QUERY :: GET CITY WEATHER LIST -->
    public LiveData<List<CurrentWeather>> getList(){
        newWeatherList.postValue(simpleList);
        return newWeatherList;
    }


    // -- FUNCTION :: PROGRESS BAR -->
    public void turnOnProgressBar(ProgressBar pProgressBar, TextView percentIndicator, FragmentActivity pActivity,
                                  View loadingView, Fragment pFragment, int fragContainer, ConstraintLayout progressContainer,
            ConstraintLayout mainDisplayView,
            ConstraintLayout restartContainer) {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String[] cities = pActivity.getResources().getStringArray(R.array.city_list);
                String api_key = pActivity.getString(R.string.api_key);
                counter ++;
                pProgressBar.setProgress((int) counter);
                setPercentIndicator(pActivity, percentIndicator);
                while (counter == step && indices < 5) {
                    step += 10;
                    getWeatherAnswer(cities[indices], api_key);
                    indices++;
                }
                 if(counter == 100){
                    timer.cancel();
                    launchFragment(loadingView, pActivity, pFragment, fragContainer);
                    updateProgressBar(pActivity, progressContainer,mainDisplayView, restartContainer);
                }
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    @SuppressLint("SetTextI18n")
    public void setPercentIndicator(Activity pActivity, TextView percentIndicator){
        pActivity.runOnUiThread(() -> percentIndicator.setText(counter + " %"));
    }

    public void setMessages(TextView indicator, Activity pActivity, String[] messages){
        Thread t = new Thread() {
            public void run() {
                repeatMessages(indicator, pActivity, messages);
            }
        };
        t.start();
        }

        public void setMessage(TextView indicator, Activity pActivity, String message){
            pActivity.runOnUiThread(() -> indicator.setText(message));
        }

        public void repeatMessages(TextView indicator, Activity pActivity, String[] messages){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(String message : messages) {
                    try {
                        setMessage(indicator, pActivity, message);
                        Thread.sleep(6000);
                    } catch (InterruptedException pE) {
                        pE.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(timerTask, 0, 18000);
        }

    public void launchFragment(View loadingView, FragmentActivity pActivity, Fragment fragment, int fragmentContainer) {
        pActivity.runOnUiThread(() -> {
            loadingView.setVisibility(View.GONE);
            FragmentManager fragmentManager = pActivity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction().replace(fragmentContainer, fragment);
            transaction.commit();
        });
    }

    private void updateProgressBar(FragmentActivity pActivity,ConstraintLayout progressContainer, ConstraintLayout mainDisplayView,ConstraintLayout restartContainer){
        pActivity.runOnUiThread(() -> {
        progressContainer.setVisibility(View.GONE);
        mainDisplayView.addView(restartContainer);
        restartContainer.setVisibility(View.VISIBLE);
        });
    }
}
