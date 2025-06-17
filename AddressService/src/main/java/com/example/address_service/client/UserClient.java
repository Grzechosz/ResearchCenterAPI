package com.example.address_service.client;

import com.example.address_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:9003")
public interface UserClient {

    @GetMapping("/users/address/{addressId}")
    List<UserDto> getUsersByAddressId(@PathVariable Long addressId);
}
