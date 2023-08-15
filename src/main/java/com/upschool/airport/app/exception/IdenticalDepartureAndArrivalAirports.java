package com.upschool.airport.app.exception;

public class IdenticalDepartureAndArrivalAirports extends RuntimeException{
    public IdenticalDepartureAndArrivalAirports(String message) {
        super(message);
    }

    public IdenticalDepartureAndArrivalAirports(String message, Throwable cause) {
        super(message, cause);
    }
}
