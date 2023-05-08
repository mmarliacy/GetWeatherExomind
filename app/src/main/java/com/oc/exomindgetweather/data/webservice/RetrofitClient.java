package com.oc.exomindgetweather.data.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static final String base_URL = "https://api.openweathermap.org/";

    // -- RETROFIT :: UNIQUE INSTANCE -->
    public static Retrofit getRetrofitClient(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
