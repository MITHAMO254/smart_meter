package com.example.smartmeter.models;


import android.widget.TextView;

public class Reading {
   public String date;
    public String previous;
    public String current;
    public String consumption;

    public Reading(String previous, String current, String date, String consumption){
        this.date = date;
        this.previous = previous;
        this.current = current;
        this.consumption = consumption;
    }

}
