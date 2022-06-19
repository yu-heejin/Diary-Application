package ddwucom.mobile.finalreport;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Diary implements Serializable {
    private long _id;
    private String title;
    private String feeling;
    private String weather;
    private String date;
    private String detail;
    private int picture;

    LocalDate localDate;
    DateTimeFormatter formatter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Diary(String title, String feeling, String weather, String detail, int picture) {
        this.title = title;
        this.feeling = feeling;
        this.weather = weather;
        this.detail = detail;
        this.picture = picture;

        localDate = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = localDate.format(formatter);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
