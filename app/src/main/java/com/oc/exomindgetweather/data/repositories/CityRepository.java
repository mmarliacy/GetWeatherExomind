package com.oc.exomindgetweather.data.repositories;

import androidx.lifecycle.LiveData;

import com.oc.exomindgetweather.data.database.Dao.CityDAO;
import com.oc.exomindgetweather.model.CityModel;

import java.util.List;

public class CityRepository {

    private final CityDAO fCityDao;

    /**
     * CONSTRUCTOR
     */
    public CityRepository(CityDAO pCityDao) {
        fCityDao = pCityDao;
    }

    // 1 -- QUERIES : -->
    //-----------------------
    // -- QUERY :: GET ALL CITIES -->
    public LiveData<List<CityModel>> getCities(){
        return this.fCityDao.getCities();
    }
}
