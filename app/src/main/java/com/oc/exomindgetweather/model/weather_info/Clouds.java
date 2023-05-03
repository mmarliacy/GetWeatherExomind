package com.oc.exomindgetweather.model.weather_info;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable {

    @SerializedName("all")
    @Expose
    private Integer all;
    public final static Creator<Clouds> CREATOR = new Creator<Clouds>() {
        public Clouds createFromParcel(android.os.Parcel in) {
            return new Clouds(in);
        }

        public Clouds[] newArray(int size) {
            return (new Clouds[size]);
        }
    };

    protected Clouds(android.os.Parcel in) {
        this.all = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Clouds() {
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(all);
    }

    public int describeContents() {
        return 0;
    }

}
