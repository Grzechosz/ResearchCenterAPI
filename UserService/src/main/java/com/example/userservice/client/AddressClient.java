package com.example.userservice.client;

import com.example.userservice.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "http://localhost:9000")
public interface AddressClient {

    @GetMapping("/api/address/user/{id}")
    AddressDto getAddressByUserId(@PathVariable("id") Long id);
}
