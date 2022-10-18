package com.example.barberapp.Model;

public class Barber {


    private String name,email,barberId;



    public Barber() {

    }

    public Barber(String name, String email,String barberId) {
        this.name = name;
        this.email = email;
        this.barberId = barberId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBarberId() {
        return barberId;
    }

    public void setBarberId(String barberId) {
        this.barberId = barberId;
    }


}

