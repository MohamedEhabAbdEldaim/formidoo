package com.midooabdaim.ardak.helper;

public class DateTxt {
    private String day, month, year, hour, mint;
    private String date_txt;

    public DateTxt(String day, String month, String year, String hour, String mint, String date_txt) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.mint = mint;
        this.date_txt = date_txt;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getDate_txt() {
        return date_txt;
    }

    public void setDate_txt(String date_txt) {
        this.date_txt = date_txt;
    }
}
