package com.denert.app.rest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AgreementNotFoundException extends  RuntimeException{

    public AgreementNotFoundException(String message) {
        super(message);
    }
}
