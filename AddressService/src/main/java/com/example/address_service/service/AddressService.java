package com.example.address_service.service;

import com.example.address_service.annotations.PublicLogger;
import com.example.address_service.annotations.ValidateAddress;
import com.example.address_service.dto.AddressDto;
import com.example.address_service.exception.AddressNotFoundException;
import com.example.address_service.models.Address;
import com.example.address_service.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@PublicLogger
@Service
@Slf4j
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;

    @ValidateAddress
    public AddressDto createAddress(AddressDto addressRequest){
        final Address address = Address.builder()
                .userId(addressRequest.getUserId())
                .city(addressRequest.getCity())
                .streetName(addressRequest.getStreetName())
                .houseNumber(addressRequest.getHouseNumber())
                .postcode(addressRequest.getPostcode())
                .flatNumber(addressRequest.getFlatNumber())
                .build();

        Address saved = addressRepo.save(address);
        AddressDto addressResponse = mapToAddressResponse(saved);

        log.info("Address {} is saved for user {}", saved.getAddressId(), saved.getUserId());
        return addressResponse;
    }


    public List<AddressDto> getAllAddresses(){
        List<Address> addresses = addressRepo.findAll();

        return addresses.stream().map(this::mapToAddressResponse).toList();
    }

    public AddressDto getAddressById(long id){
        return addressRepo.findById(id)
                .map(this::mapToAddressResponse)
                .orElseThrow(() -> new AddressNotFoundException("Address with ID " + id + " not found."));
    }

    @ValidateAddress
    public AddressDto updateAddress(AddressDto addressRequest, long id) {
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

    public AddressDto getAddressByUserId(long userId) {
        return addressRepo.findByUserId(userId)
                .map(this::mapToAddressResponse)
                .orElseThrow(() -> new AddressNotFoundException("Address for user ID " + userId + " not found"));
    }

    private AddressDto mapToAddressResponse(Address address) {
        return AddressDto.builder()
                .addressId(address.getAddressId())
                .city(address.getCity())
                .streetName(address.getStreetName())
                .houseNumber(address.getHouseNumber())
                .postcode(address.getPostcode())
                .flatNumber(address.getFlatNumber())
                .userId(address.getUserId())
                .build();
    }


}
