package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class Diary implements Serializable {
    private long _id;
    private String title;
    private String feeling;
    private String weather;
    private String date;
    private String detail;
    private int picture;

    public Diary(long _id, String title, String feeling, String weather, String date, String detail, int picture) {
        this._id = _id;
        this.title = title;
        this.feeling = feeling;
        this.weather = weather;
        this.date = date;
        this.detail = detail;
        this.picture = picture;
    }

    public Diary(String title, String feeling, String weather, String detail) {
        this.title = title;
        this.feeling = feeling;
        this.weather = weather;
        this.detail = detail;
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
