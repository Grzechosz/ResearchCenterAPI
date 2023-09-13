package com.denert.app.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestCommissionNotFoundException extends RuntimeException{
    public TestCommissionNotFoundException(String message) {
        super(message);
    }
}
