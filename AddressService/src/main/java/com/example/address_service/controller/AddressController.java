package com.example.address_service.controller;

import com.example.address_service.dto.AddressDto;
import com.example.address_service.service.AddressService;
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
    public List<AddressDto> getAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping(value = "/{id}")
    public AddressDto getAddress(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AddressDto> getAddressByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.getAddressByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressRequest) {
        AddressDto savedAddress = addressService.createAddress(addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable long id, @RequestBody AddressDto addressRequest) {
        AddressDto addressResponse = addressService.updateAddress(addressRequest,id);

        return ResponseEntity.ok(addressResponse);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
