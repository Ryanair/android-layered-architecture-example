package com.ryanair.cheapflights.domain.command;


import com.ryanair.cheapflights.entity.FlightInfo;
import com.ryanair.cheapflights.repository.FlightInfoRepository;

import java.util.List;

import rx.Observable;

public class GetFlightInfo {
    private final FlightInfoRepository repository;

    public GetFlightInfo(FlightInfoRepository repository) {
        this.repository = repository;
    }

    public Observable<List<FlightInfo>> execute(final String flightNumber) {
        return repository.getFlightInfo(flightNumber);
    }
}
