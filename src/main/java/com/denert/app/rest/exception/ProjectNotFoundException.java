package com.denert.app.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
