package com.oc.exomindgetweather.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oc.exomindgetweather.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherListFragment extends Fragment {

    public static WeatherListFragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }
}