package com.example.barberapp.Model;

public class Shifts  {

    private String ID;
    private String Date;
    private String Hour;
    private String min;



    public Shifts(String Hour){
        this.Hour = Hour;
    }

    public Shifts( String date, String hour,String min) {

        this.Date = date;
        this.Hour = hour;
        this.min = min;
    }

    public Shifts(String hourOfEnd, String minuteOfEnd) {

    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }
}
