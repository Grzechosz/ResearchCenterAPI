package com.example.projectservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestCommissionNotFoundException extends RuntimeException{
    public TestCommissionNotFoundException(String message) {
        super(message);
    }
}
