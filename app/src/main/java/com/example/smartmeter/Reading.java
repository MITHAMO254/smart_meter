package com.example.smartmeter;


import android.widget.TextView;

public class Reading {
    String date;
    String previous;
    String current;
    String consumption;

    public Reading(String previous, String current, String date, String consumption){
        this.date = date;
        this.previous = previous;
        this.current = current;
        this.consumption = consumption;
    }

}
