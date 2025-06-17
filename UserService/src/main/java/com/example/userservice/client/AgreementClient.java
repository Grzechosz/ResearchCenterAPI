package com.example.userservice.client;

import com.example.userservice.dto.AddressDto;
import com.example.userservice.dto.AgreementDto;
import com.example.userservice.dto.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
@FeignClient(name = "agreement-service", url = "http://localhost:9001")
public interface AgreementClient {
    @GetMapping("/api/agreements/user/{id}")
    List<AgreementDto> getAgreementsByUserId(@PathVariable Long id);
}
