package com.example.agreementservice.client;

import com.example.agreementservice.configuration.FeignConfig;
import com.example.agreementservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",
        url = "http://user-app:9003",
        configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/api/users/{userId}")
    UserDto getUserById(@PathVariable Long userId);
}
