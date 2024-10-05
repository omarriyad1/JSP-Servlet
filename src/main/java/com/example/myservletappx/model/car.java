package com.example.myservletappx.model;

import java.util.HashMap;
import java.util.Map;

public class car {
    private String id;

    private String model;
    private String make;
    private int year;
    // Default constructor for Jackson
    public car() {}

    // Constructor, getters, setters
    public car(String id, String model, String make, int year) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.year = year;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

}
