package com.oc.exomindgetweather.data.database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.oc.exomindgetweather.model.CityModel;

import java.util.List;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM city_table")
    LiveData<List<CityModel>>getCities();
}
