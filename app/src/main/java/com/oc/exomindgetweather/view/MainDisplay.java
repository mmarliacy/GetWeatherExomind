package com.oc.exomindgetweather.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.databinding.ActivityMainDisplayBinding;
import com.oc.exomindgetweather.fragment.WeatherListFragment;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

public class MainDisplay extends AppCompatActivity {

    //-----------
    // VARIABLES
    //-----------
    public ActivityMainDisplayBinding mainBinding;
    private int counter = 0;
    TextView percent;
    TextView indicator;
    ProgressBar progressBar;
    int fragContainer;
    ConstraintLayout progressBarContainer;
    ConstraintLayout mainContainer;
    ConstraintLayout restartContainer;
    String[] messages;
    View loadingView;
    WeatherViewModel viewModel;


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainDisplayBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        indicator = mainBinding.indicator;
        percent = mainBinding.percentTxt;
        mainContainer = mainBinding.mainContainer;
        restartContainer = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.restart_view, null);
        progressBarContainer = mainBinding.progressBarContainer;
        fragContainer = mainBinding.fragmentContainer.getId();
        progressBar = mainBinding.progressBar;
        loadingView = LayoutInflater.from(this).inflate(R.layout.fragment_loading_view, null);
        messages = getResources().getStringArray(R.array.messages);


        configureViewModel();
        viewModel.makeProgressBarOn(
                progressBar,
                percent,
                this,
                loadingView,
                WeatherListFragment.newInstance(),
                fragContainer,
                progressBarContainer, mainContainer, restartContainer);
        viewModel.makeMessageRotation(indicator, this, messages);
        configureLoadingView();
        configureBackButton();
    }

    //-- Configure and Init conditions of View Model --
    private void configureViewModel() {
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory();
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        this.viewModel.initCitiesList();
    }



    @SuppressLint("InflateParams")
    private void configureLoadingView() {
        FrameLayout container = mainBinding.fragmentContainer;
        container.addView(loadingView);
        loadingView.setVisibility(View.VISIBLE);
    }

    public void launchFragment(View loadingView, FragmentActivity pActivity, Fragment fragment, int fragmentContainer) {
            loadingView.setVisibility(View.GONE);
            FragmentManager fragmentManager = pActivity.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction().replace(fragmentContainer, fragment);
            transaction.commit();
    }

    private void configureBackButton(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        @SuppressLint("UseCompatLoadingForDrawables")
        final Drawable backArrow = getResources().getDrawable(R.drawable.back_arrow);
        actionBar.setHomeAsUpIndicator(backArrow);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}