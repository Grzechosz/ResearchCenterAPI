package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.Address;
import com.denert.app.rest.Repo.AdresRepo;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AdresRepo adresRepo;

    @GetMapping(value = "/addresses")
    public List<Address> getAddresses() {
        return adresRepo.findAll();
    }

    @GetMapping(value = "/addresses/{id}")
    public Address getAddress(@PathVariable long id) {
        return adresRepo.findById(id).get();
    }

    @PostMapping(value = "/addresses")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        adresRepo.save(address);
        return ResponseEntity.ok(address);
    }

    @PutMapping(value = "/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody Address address) {
        Address updateAddress = adresRepo.findById(id).get();
        updateAddress.setCity(address.getCity());
        updateAddress.setStreetName(address.getStreetName());
        updateAddress.setHouseNumber(address.getHouseNumber());
        updateAddress.setPostcode(address.getPostcode());
        updateAddress.setFlatNumber(address.getFlatNumber());

        adresRepo.save(updateAddress);

        return ResponseEntity.ok(updateAddress);
    }

    @DeleteMapping(value = "/addresses/{id}")
    public String deleteAddress(@PathVariable long id) {
        Address deleteAddress = adresRepo.findById(id).get();
        adresRepo.delete(deleteAddress);
        return "Deleted address with id: "+id;
    }
}
