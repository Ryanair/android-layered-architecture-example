package com.ryanair.cheapflights.data.api.dotRez.model.flightinformation;

public class FlightInfoRequest {
    private String departure;
    private String arrival;
    private String flight;

    public FlightInfoRequest(String flight) {
        this.flight = flight;
    }

    public FlightInfoRequest(String arrival, String departure) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getFlight() {
        return flight;
    }
}
