package com.oc.exomindgetweather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

public class HomeMenu extends AppCompatActivity {

    public static String api_key;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        startLoadingActivity();
        configureViewModel();
    }

    //-- Configure and Init conditions of View Model --
    private void configureViewModel() {
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        this.viewModel.initCitiesList();
    }


    private void startLoadingActivity(){
        Button varGoBtn = findViewById(R.id.go_btn);
        varGoBtn.setOnClickListener(v-> startActivity(new Intent(this, MainDisplay.class)));
    }

}