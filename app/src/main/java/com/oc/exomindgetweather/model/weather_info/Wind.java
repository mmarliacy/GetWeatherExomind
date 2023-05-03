package com.oc.exomindgetweather.model.weather_info;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind implements Parcelable {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    public final static Creator<Wind> CREATOR = new Creator<Wind>() {


        public Wind createFromParcel(android.os.Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return (new Wind[size]);
        }

    };

    protected Wind(android.os.Parcel in) {
        this.speed = ((Double) in.readValue((Double.class.getClassLoader())));
        this.deg = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Wind() {
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(speed);
        dest.writeValue(deg);
    }

    public int describeContents() {
        return 0;
    }

}
