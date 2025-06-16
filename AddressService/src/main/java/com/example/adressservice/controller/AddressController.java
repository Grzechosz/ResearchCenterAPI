package com.example.adressservice.controller;

import com.example.adressservice.dto.AddressRequest;
import com.example.adressservice.dto.AddressResponse;
import com.example.adressservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping(value = "/{id}")
    public AddressResponse getAddress(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressRequest> saveAddress(@RequestBody AddressRequest addressRequest) {
        addressService.createAddress(addressRequest);
        return ResponseEntity.ok(addressRequest);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable long id, @RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.updateAddress(addressRequest,id);

        return ResponseEntity.ok(addressResponse);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
