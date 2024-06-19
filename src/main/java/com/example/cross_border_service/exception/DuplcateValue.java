package com.example.cross_border_service.exception;

public class DuplcateValue extends RuntimeException {
    private static final Long SerialVersionUID = 1L;

    public DuplicateValue(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateValue(String message) {
        super(message);
    }
}
