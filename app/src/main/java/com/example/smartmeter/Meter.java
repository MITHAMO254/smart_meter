package com.example.smartmeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Meter {
    private HashMap<String, Integer> meters = new HashMap();
    public Meter(){
        meters.put("Muraya Home", 7528911);
        meters.put("John Matasia", 7528906);
        meters.put("Munene Corner baridi", 7528910);
        meters.put("Githeiga Oloolua", 7528898);
        meters.put("Mutall building",7528901);
    }
    public int getMeter(String name) throws NullPointerException{
        return meters.get(name);
    }

}


