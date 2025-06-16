package com.example.adressservice.service;

import com.example.adressservice.dto.AddressRequest;
import com.example.adressservice.dto.AddressResponse;
import com.example.adressservice.exception.AddressNotFoundException;
import com.example.adressservice.models.Address;
import com.example.adressservice.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;

    public void createAddress(AddressRequest addressRequest){
        final Address address = Address.builder()
                .city(addressRequest.getCity())
                .streetName(addressRequest.getStreetName())
                .houseNumber(addressRequest.getHouseNumber())
                .postcode(addressRequest.getPostcode())
                .flatNumber(addressRequest.getFlatNumber())
                .users(addressRequest.getUsers())
                .build();
        AddressResponse addressResponse = mapToAddressResponse(addressRepo.save(address));
        log.info("Address {} is saved", address.getAddressId());

    }

    public List<AddressResponse> getAllAddresses(){
        List<Address> addresses = addressRepo.findAll();

        return addresses.stream().map(this::mapToAddressResponse).toList();
    }

    public AddressResponse getAddressById(long id){
        return addressRepo.findById(id)
                .map(this::mapToAddressResponse)
                .orElseThrow(() -> new AddressNotFoundException("Address with ID " + id + " not found."));
    }

    public AddressResponse updateAddress(AddressRequest addressRequest, long id) {
        return addressRepo.findById(id)
                .map(existingAddress -> {
                    existingAddress.setPostcode(addressRequest.getPostcode());
                    existingAddress.setCity(addressRequest.getCity());
                    existingAddress.setStreetName(addressRequest.getStreetName());
                    existingAddress.setFlatNumber(addressRequest.getFlatNumber());
                    existingAddress.setHouseNumber(addressRequest.getHouseNumber());

                    Address updatedAddress = addressRepo.save(existingAddress);
                    log.info("Address {} was updated", updatedAddress.getAddressId());
                    return mapToAddressResponse(updatedAddress);

                })  .orElseThrow(() -> new AddressNotFoundException("Address with ID " + id + " not found"));
    }

    public void deleteAddress(long id){
        Address addressToDelete = addressRepo.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address with ID " + id + " not found."));
        addressRepo.delete(addressToDelete);
        log.info("Address {} was deleted", id);
    }

    private AddressResponse mapToAddressResponse(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .city(address.getCity())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .postcode(address.getPostcode())
                .flatNumber(address.getFlatNumber())
                .users(address.getUsers())
                .build();
    }
}
