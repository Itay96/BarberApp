package com.example.barberapp.Model;

public class Appointment {

    private String date;
    private String hour;
    private Boolean available;



    public Appointment(String day, String hour, Boolean available) {
        this.date = day;
        this.hour = hour;
        this.available = available;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
