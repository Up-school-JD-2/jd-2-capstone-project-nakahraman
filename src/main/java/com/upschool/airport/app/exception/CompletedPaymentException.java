package com.upschool.airport.app.exception;

public class CompletedPaymentException extends RuntimeException{

    public CompletedPaymentException(String message) {
        super(message);
    }

    public CompletedPaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
