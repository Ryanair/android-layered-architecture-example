package com.ryanair.cheapflights.repository;

import com.ryanair.cheapflights.entity.Airport;
import com.ryanair.cheapflights.entity.FlightInfo;
import com.ryanair.cheapflights.data.api.dotRez.model.flightinformation.FlightInfoRequest;
import com.ryanair.cheapflights.data.api.dotRez.model.flightinformation.FlightInfoResponse;
import com.ryanair.cheapflights.data.api.dotRez.service.FlightInfoService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class FlightInfoRepository {
    private final FlightInfoService service;

    public FlightInfoRepository(FlightInfoService service) {
        this.service = service;
    }

    public Observable<List<FlightInfo>> getFlightInfo(String flightNumber) {
        FlightInfoRequest request = new FlightInfoRequest(flightNumber);

        return service.getFlightInfo(request)
                .map(new Func1<FlightInfoResponse, List<FlightInfo>>() {
                    @Override
                    public List<FlightInfo> call(FlightInfoResponse flightInfoResponse) {
                        List<FlightInfo> results = new ArrayList<>();
                        if (flightInfoResponse != null) {
                            FlightInfoResponse.FlightInfoItem[] flightInfoItems = flightInfoResponse.getFlightInfo();
                            if (flightInfoItems != null) {
                                for (FlightInfoResponse.FlightInfoItem item : flightInfoItems) {
                                    results.add(mapToFlightInfo(item));
                                }
                            }
                        }
                        return results;
                    }
                });
    }

    private FlightInfo mapToFlightInfo(FlightInfoResponse.FlightInfoItem item) {
        FlightInfo flightInfo = new FlightInfo();
        flightInfo.setArrivalAirport(mapToAirport(item.getArrivalAirport()));
        flightInfo.setDepartureAirport(mapToAirport(item.getDepartureAirport()));
        flightInfo.setArrivalTime(item.getArrivalTime());
        flightInfo.setDepartureTime(item.getDepartureTime());
        flightInfo.setOpen(item.isOpen());
        flightInfo.setFlightNumber(item.getFlightNumber());
        if (item.getDescription() != null) {
            flightInfo.setMessageCode(item.getDescription().getCode());
            flightInfo.setMessage(item.getDescription().getMessage());
        }

        return flightInfo;
    }

    private Airport mapToAirport(FlightInfoResponse.FlightInfoAirport flightInfoAirport) {
        return new Airport()
            .setIATA(flightInfoAirport.getIata())
            .setName(flightInfoAirport.getName());
    }
}
