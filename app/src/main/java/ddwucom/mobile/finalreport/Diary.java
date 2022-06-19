package ddwucom.mobile.finalreport;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable {
    private long _id;
    private String title;
    private String feeling;
    private String weather;
    private Date date;
    private String detail;

    public Diary(String title, String feeling, String weather, Date date, String detail) {
        this.title = title;
        this.feeling = feeling;
        this.weather = weather;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
