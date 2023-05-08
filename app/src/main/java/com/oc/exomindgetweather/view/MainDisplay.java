package com.oc.exomindgetweather.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.databinding.ActivityMainDisplayBinding;
import com.oc.exomindgetweather.fragment.LoadingFrag;
import com.oc.exomindgetweather.fragment.WeatherListFragment;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

public class MainDisplay extends AppCompatActivity {

    //-----------
    // VARIABLES
    //-----------
    public ActivityMainDisplayBinding mainBinding;
    int fragContainer;
    WeatherViewModel viewModel;


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainDisplayBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        fragContainer = mainBinding.fragmentContainer.getId();

        configureViewModel();
        onStartLaunchLoadingFrag();
        onStartLaunchProgressBar();
        onStartLaunchMessagesRotation();
    }

    // 1 -- View Model -->
    private void configureViewModel() {
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory();
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
    }

    // 2 -- Launch loading fragment -->
    private void onStartLaunchLoadingFrag(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(fragContainer, new LoadingFrag());
        transaction.setReorderingAllowed(true).commit();
    }

    // 3 -- Launch progress bar process -->
    private void onStartLaunchProgressBar(){
        TextView percent = mainBinding.percentTxt;
        ProgressBar progressBar = mainBinding.progressBar;
        ViewGroup bottomContainer = mainBinding.bottomContainer;
        fragContainer = mainBinding.fragmentContainer.getId();
        viewModel.makeProgressBarOn(
                progressBar,
                percent,
                MainDisplay.this,
                bottomContainer, WeatherListFragment.newInstance(), fragContainer);
    }

    // 4 -- Launch messages process -->
    private void onStartLaunchMessagesRotation(){
        TextView indicator = mainBinding.indicator;
        String[] messages = getResources().getStringArray(R.array.messages);
        viewModel.makeMessageRotation(indicator, this, messages);
    }
}