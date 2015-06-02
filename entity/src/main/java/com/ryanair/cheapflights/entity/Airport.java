package com.ryanair.cheapflights.entity;

public class Airport {
    private String iata;
    private String name;

    public String getIATA() {
        return iata;
    }

    public Airport setIATA(String iata) {
        this.iata = iata;
        return this;
    }

    public String getName() {
        return name;
    }

    public Airport setName(String name) {
        this.name = name;
        return this;
    }
}
