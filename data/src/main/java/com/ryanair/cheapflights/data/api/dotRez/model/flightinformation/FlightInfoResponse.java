package com.ryanair.cheapflights.data.api.dotRez.model.flightinformation;

import com.google.gson.annotations.SerializedName;

public class FlightInfoResponse {
    private int total;
    private FlightInfoItem[] flightInfo;

    public int getTotal() {
        return total;
    }

    public FlightInfoItem[] getFlightInfo() {
        return flightInfo;
    }

    public FlightInfoResponse setFlightInfo(FlightInfoItem[] flightInfo) {
        this.flightInfo = flightInfo;
        return this;
    }

    public class FlightInfoAirport {
        private String iata;
        private String name;

        public String getIata() {
            return iata;
        }

        public String getName() {
            return name;
        }
    }

    public class FlightInfoItem {
        @SerializedName("number")
        private String flightNumber;
        private FlightInfoAirport departureAirport;
        private FlightInfoAirport arrivalAirport;
        private String departureTime;
        private String arrivalTime;
        private boolean open;
        private FlightInfoDescription description;

        public String getFlightNumber() {
            return flightNumber;
        }

        public FlightInfoAirport getDepartureAirport() {
            return departureAirport;
        }

        public FlightInfoAirport getArrivalAirport() {
            return arrivalAirport;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }

        public boolean isOpen() {
            return open;
        }

        public FlightInfoDescription getDescription() {
            return description;
        }

        public FlightInfoItem setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }
    }

    public class FlightInfoDescription {
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
