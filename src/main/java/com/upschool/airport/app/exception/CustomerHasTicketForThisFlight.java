package com.upschool.airport.app.exception;

public class CustomerHasTicketForThisFlight extends RuntimeException{
    public CustomerHasTicketForThisFlight(String message) {
        super(message);
    }

    public CustomerHasTicketForThisFlight(String message, Throwable cause) {
        super(message, cause);
    }
}
