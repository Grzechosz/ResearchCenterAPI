package com.example.userservice.client;

import com.example.userservice.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "address-service", url = "http://localhost:9000")
public interface AddressClient {

    @GetMapping("/api/address/user/{id}")
    AddressDto getAddressByUserId(@PathVariable("id") Long id);

    @PostMapping("/api/address")
    ResponseEntity<AddressDto> addAddressByUser(@RequestBody AddressDto address);
}
