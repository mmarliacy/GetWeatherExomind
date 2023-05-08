package com.oc.exomindgetweather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.adapter.WeatherAdapter;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.model.CurrentWeather;
import com.oc.exomindgetweather.view.HomeMenu;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;
import java.util.ArrayList;
import java.util.List;

public class WeatherListFragment extends Fragment {

    //-----------
    // VARIABLES
    //-----------
    private WeatherViewModel viewModel;
    private final List<CurrentWeather> fWeatherList = new ArrayList<>();
    private final WeatherAdapter adapter = new WeatherAdapter(fWeatherList);

    // -- FRAGMENT :: INSTANCE -->
    public static WeatherListFragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);

        startViewModel();
        getCityWeather();
        configureRecyclerView(view);
        backToMainMenu(view);
        return view;
    }

    // 1 -- View Model -->
    private void startViewModel(){
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory();
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        this.viewModel.initCitiesList();
    }

    // 2 -- Get observable weather list -->
    private void getCityWeather() {
        this.viewModel.getFinalList().observe(requireActivity(), adapter::setCitiesList);
    }

    // 3 -- Set recycler view -->
    private void configureRecyclerView(View view){
        RecyclerView varRv = view.findViewById(R.id.recycler_view);
        varRv.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        varRv.setAdapter(adapter);
    }

    // 4 -- Configure back to main menu -->
    private void backToMainMenu(View view){
        FloatingActionButton varBackButton = view.findViewById(R.id.back_button);
        varBackButton.setOnClickListener(pView -> startActivity(new Intent(requireContext(), HomeMenu.class)));
    }
}