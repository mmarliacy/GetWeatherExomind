package com.oc.exomindgetweather.model.weather_info;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main implements Parcelable {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    public final static Creator<Main> CREATOR = new Creator<Main>() {


        public Main createFromParcel(android.os.Parcel in) {
            return new Main(in);
        }

        public Main[] newArray(int size) {
            return (new Main[size]);
        }

    };

    protected Main(android.os.Parcel in) {
        this.temp = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pressure = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.humidity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tempMin = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tempMax = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Main() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(temp);
        dest.writeValue(pressure);
        dest.writeValue(humidity);
        dest.writeValue(tempMin);
        dest.writeValue(tempMax);
    }

    public int describeContents() {
        return 0;
    }

}
