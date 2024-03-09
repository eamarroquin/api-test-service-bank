package com.bluesoft.servicebank.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String detail) {
        super(detail);
    }

}
