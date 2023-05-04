package com.oc.exomindgetweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.databinding.ActivityHomeMenuBinding;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

public class HomeMenu extends AppCompatActivity {

    private String api_key;
    private WeatherViewModel viewModel;
    ActivityHomeMenuBinding fHomeMenuBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fHomeMenuBinding = ActivityHomeMenuBinding.inflate(getLayoutInflater());
        setContentView(fHomeMenuBinding.getRoot());
        startLoadingActivity();
        configureViewModel();
        api_key = getResources().getString(R.string.api_key);
    }

    //-- Configure and Init conditions of View Model --
    private void configureViewModel() {
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory();
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        this.viewModel.initCitiesList();
    }

    private void startLoadingActivity(){
        Button varGoBtn = findViewById(R.id.go_btn);
        varGoBtn.setOnClickListener(v-> startActivity(new Intent(this, MainDisplay.class)));
    }


}