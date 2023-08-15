package com.upschool.airport.app.exception;

public class FlightCapacityExceededException extends RuntimeException{
    public FlightCapacityExceededException(String message) {
        super(message);
    }

    public FlightCapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
