package com.oc.exomindgetweather.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.adapter.WeatherAdapter;
import com.oc.exomindgetweather.data.factory.Injection;
import com.oc.exomindgetweather.data.factory.WeatherFactory;
import com.oc.exomindgetweather.viewmodel.WeatherViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherListFragment extends Fragment {

    public WeatherViewModel viewModel;
    RecyclerView rv;
    WeatherAdapter adapter;

    public static WeatherListFragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);
        adapter = new WeatherAdapter(requireContext());
        startViewModel();
        configureRecyclerView(view);
        return view;

    }

    private void startViewModel(){
        WeatherFactory viewModelFactory = Injection.provideViewModelFactory();
        this.viewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
    }

    private void configureRecyclerView(View view){
        getCityWeather();
        rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    private void getCityWeather() {

        this.viewModel.getFinalList().observe(requireActivity(), pCurrentWeathers -> {
            adapter.setCitiesList(pCurrentWeathers);
        });
    }
}