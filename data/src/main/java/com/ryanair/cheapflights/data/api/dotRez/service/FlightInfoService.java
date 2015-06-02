package com.ryanair.cheapflights.data.api.dotRez.service;

import com.ryanair.cheapflights.data.api.dotRez.model.flightinformation.FlightInfoRequest;
import com.ryanair.cheapflights.data.api.dotRez.model.flightinformation.FlightInfoResponse;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface FlightInfoService {
    @POST("/.../")
    Observable<FlightInfoResponse> getFlightInfo(@Body FlightInfoRequest request);
}
