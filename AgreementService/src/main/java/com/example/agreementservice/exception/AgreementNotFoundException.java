package com.example.agreementservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AgreementNotFoundException extends  RuntimeException{

    public AgreementNotFoundException(String message) {
        super(message);
    }
}
