package com.denert.app.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestResultsNotFoundException  extends RuntimeException{
    public TestResultsNotFoundException(String message) {
        super(message);
    }
}
