package com.example.cross_border_service.exception;

public class ViolationIntegrity extends RuntimeException {

    private static final Long SerialVersionUID = 1L;

    public ViolationIntegrity(String message, Throwable cause) {
        super(message, cause);
    }

    public ViolationIntegrity(String message) {
        super(message);
    }

}
