package com.midooabdaim.ardak.helper;

public class TimeTXT {

    private String hours, mint;
    private String time_txt;

    public TimeTXT(String hours, String mint, String time_txt) {
        this.hours = hours;
        this.mint = mint;
        this.time_txt = time_txt;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getTime_txt() {
        return time_txt;
    }

    public void setTime_txt(String time_txt) {
        this.time_txt = time_txt;
    }
}
