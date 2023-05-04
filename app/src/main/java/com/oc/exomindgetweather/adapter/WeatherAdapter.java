package com.oc.exomindgetweather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oc.exomindgetweather.R;
import com.oc.exomindgetweather.model.CurrentWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<CurrentWeather> citiesList;
    private Context fContext;

    public WeatherAdapter(Context pContext) {
        citiesList = new ArrayList<>();
        fContext = pContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCitiesList(List<CurrentWeather> pCurrentWeatherList){
        this.citiesList = pCurrentWeatherList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fContext).inflate(R.layout.weather_item, parent, false);
        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
            CurrentWeather cityWeather = citiesList.get(position);
            holder.bind(cityWeather);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView city_txt ;
        TextView temperature_txt;
        ImageView weather_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(CurrentWeather pCurrentWeather){
            city_txt = itemView.findViewById(R.id.item_city_name);
            temperature_txt = itemView.findViewById(R.id.item_temperature);
            weather_icon = itemView.findViewById(R.id.item_weather_picture);

            city_txt.setText(pCurrentWeather.getName());
            temperature_txt.setText(pCurrentWeather.getMain().getTemp().toString());
            String icon = pCurrentWeather.getWeather().get(0).getIcon();
            Picasso.get().load("https://openweathermap.org/img/wn/" + icon +"@2x.png").into(weather_icon);
        }
    }
}
