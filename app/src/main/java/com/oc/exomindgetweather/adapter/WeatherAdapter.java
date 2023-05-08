package com.oc.exomindgetweather.adapter;

import android.annotation.SuppressLint;
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
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<CurrentWeather> citiesList;

    /** CONSTRUCTOR */
    public WeatherAdapter(List<CurrentWeather> pCitiesList) {
        citiesList = pCitiesList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCitiesList(List<CurrentWeather> pCurrentWeatherList){
        this.citiesList = pCurrentWeatherList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(CurrentWeather pCurrentWeather){
            TextView city_txt = itemView.findViewById(R.id.item_city_name);
            TextView temperature_txt = itemView.findViewById(R.id.item_temperature);
            ImageView weather_icon = itemView.findViewById(R.id.item_weather_picture);

            city_txt.setText(pCurrentWeather.getName());
            temperature_txt.setText(pCurrentWeather.getMain().getTemp().toString() + " C°");
            String icon = pCurrentWeather.getWeather().get(0).getIcon();
            Picasso.get().load("https://openweathermap.org/img/w/" + icon +".png").into(weather_icon);
        }
    }
}
