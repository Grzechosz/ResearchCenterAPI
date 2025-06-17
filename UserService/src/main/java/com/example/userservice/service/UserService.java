package com.example.userservice.service;

import com.example.userservice.client.AddressClient;
import com.example.userservice.client.AgreementClient;
import com.example.userservice.dto.AddressDto;
import com.example.userservice.dto.AgreementDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserIdsDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.models.User;
import com.example.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AddressClient addressClient;
    private final AgreementClient agreementClient;

    public UserDto createUser(UserDto userRequest) {
        // 1. Tworzenie i zapis użytkownika (bez addressId)
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .phoneNumber(userRequest.getPhoneNumber())
                .mail(userRequest.getMail())
                .build();
        user = userRepo.save(user);

        if (userRequest.getAddress() != null) {
            try {
                AddressDto address = userRequest.getAddress();
                address.setUserId(user.getUserId());

                ResponseEntity<AddressDto> addressResponse = addressClient.addAddressByUser(address);

                if (addressResponse.getStatusCode().is2xxSuccessful() && addressResponse.getBody() != null) {
                    user.setAddressId(addressResponse.getBody().getAddressId());
                    user = userRepo.save(user);
                } else {
                    log.warn("Address service returned unsuccessful status: {}", addressResponse.getStatusCode());
                }

            } catch (Exception e) {
                log.error("Error while creating address for user: {}", e.getMessage());
            }
        }

        log.info("User {} is saved", user.getUserId());

        return mapToUserResponse(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public UserIdsDto getUserById(long id) {
        return userRepo.findById(id)
                .map(this::mapToUserResponseIds)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    public UserDto updateUser(UserDto userRequest, long id) {
        return userRepo.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userRequest.getName());
                    existingUser.setSurname(userRequest.getSurname());
                    existingUser.setPhoneNumber(userRequest.getPhoneNumber());
                    existingUser.setMail(userRequest.getMail());

                    User updatedUser = userRepo.save(existingUser);
                    log.info("User {} was updated", updatedUser.getUserId());
                    return mapToUserResponse(updatedUser);

                })  .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public void deleteUser(long id) {
        User userToDelete = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));

        userRepo.delete(userToDelete);
        log.info("User {} was deleted", id);
    }

    private UserIdsDto mapToUserResponseIds(User user) {
        AddressDto address = null;
        List<AgreementDto> agreements = new ArrayList<>();

        try {
            address = addressClient.getAddressByUserId(user.getUserId());
        } catch (Exception e) {
            log.warn("Could not fetch address for user {}: {}", user.getUserId(), e.getMessage());
        }


        return UserIdsDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .mail(user.getMail())
                .address(address)
                .build();
    }

    private UserDto mapToUserResponse(User user) {
        AddressDto address = null;
        List<AgreementDto> agreements = new ArrayList<>();

        try {
            address = addressClient.getAddressByUserId(user.getUserId());
        } catch (Exception e) {
            log.warn("Could not fetch address for user {}: {}", user.getUserId(), e.getMessage());
        }

        try {
            agreements = agreementClient.getAgreementsByUserId(user.getUserId());
        } catch (Exception e) {
            log.warn("Could not fetch agreements for user {}: {}", user.getUserId(), e.getMessage());
        }

        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .mail(user.getMail())
                .address(address)
                .projectsAgreements(agreements)
                .build();
    }
}
