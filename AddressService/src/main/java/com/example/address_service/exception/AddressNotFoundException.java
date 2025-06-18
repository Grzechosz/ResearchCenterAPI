package com.example.address_service.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
}
