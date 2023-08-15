package com.upschool.airport.app.exception;

public class NoSuchEntityFoundException extends RuntimeException{

    public NoSuchEntityFoundException(String message) {
        super(message);
    }

    public NoSuchEntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
