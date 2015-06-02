package com.ryanair.cheapflights.presentation.view;

import com.ryanair.cheapflights.entity.FlightInfo;

import java.util.List;

public interface FlightInfoResultsView {
    String getFlightNumber();

    void displayFlightInfo(List<FlightInfo> flightInfo);

    void displayError(Throwable error);

    void displaySelectedFlight(FlightInfo flightInfo);
}
