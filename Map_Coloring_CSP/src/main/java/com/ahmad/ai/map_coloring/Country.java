package com.ahmad.ai.map_coloring;

public class Country {
    public String name;
    public int id;
    public int color = -1; // -1 means uncolored
    public int borderCount;

    public Country(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

