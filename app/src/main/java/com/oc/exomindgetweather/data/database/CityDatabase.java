package com.oc.exomindgetweather.data.database;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.oc.exomindgetweather.data.database.Dao.CityDAO;
import com.oc.exomindgetweather.model.CityModel;


@Database(entities = {CityModel.class}, version = 1, exportSchema = false)
public abstract class CityDatabase extends RoomDatabase {

    // 1 -- DATABASE INSTANCE -->
    private static volatile CityDatabase instance;

    // 2 -- DAO -->
    public abstract CityDAO fCityDAO();

    // 3 -- SINGLETON PATTERN -->
    public static CityDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (CityDatabase.class) {
                instance = Room.databaseBuilder(
                                context.getApplicationContext(),
                                CityDatabase.class,
                                "database").
                        addCallback(cityCallBack()).
                        build();
            }
        }
        return instance;
    }

    // 4 -- Create Callback -->
    private static Callback cityCallBack() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                prepopulateDatabaseWithCity(db);
                Log.d("DATABASE : " ,"prepopulateDatabase(db) has been called successfully ");
            }
        };
    }

    private static void prepopulateDatabaseWithCity( SupportSQLiteDatabase db) {
        CityModel[] cityList = CityModel.getCities();
        for (CityModel city : cityList){
            ContentValues cityValues = new ContentValues();
            cityValues.put("id", city.getId());
            cityValues.put("cityName", city.getCityName());
            cityValues.put("lat", city.getLat());
            cityValues.put("lon", city.getLon());
            db.insert("city_table", OnConflictStrategy.IGNORE, cityValues);
        }
    }
}
