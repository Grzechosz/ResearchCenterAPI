package com.example.projectservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestResultsNotFoundException  extends RuntimeException{
    public TestResultsNotFoundException(String message) {
        super(message);
    }
}
