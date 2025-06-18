package com.example.address_service.aspects;

import com.example.address_service.annotations.ValidateAddress;
import com.example.address_service.dto.AddressDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Around("@annotation(validateAddress)")
    public Object validateAddress(ProceedingJoinPoint pjp, ValidateAddress validateAddress) throws Throwable {
        Object[] args = pjp.getArgs();

        for (Object arg : args) {
            if (arg instanceof AddressDto addressDto) {
                validateAddressFields(addressDto);
            }
        }

        return pjp.proceed();
    }

    private void validateAddressFields(AddressDto addressDto) {
        StringBuilder errorBuilder = new StringBuilder();

        if (addressDto.getCity() == null || addressDto.getCity().isBlank()) {
            errorBuilder.append("City is missing or blank. ");
        }
        if (addressDto.getStreetName() == null || addressDto.getStreetName().isBlank()) {
            errorBuilder.append("Street name is missing or blank. ");
        }
        if (addressDto.getHouseNumber() == null || addressDto.getHouseNumber().isBlank()) {
            errorBuilder.append("House number is missing or blank. ");
        }
        if (addressDto.getPostcode() <= 0) {
            errorBuilder.append("Postcode must be greater than 0. ");
        }
        if (addressDto.getFlatNumber() < 0) {
            errorBuilder.append("Flat number cannot be negative. ");
        }

        if (!errorBuilder.isEmpty()) {
            throw new IllegalArgumentException("Invalid Address: " + errorBuilder.toString().trim());
        }
    }
}
