package com.example.userservice.service;

import com.example.userservice.client.AddressClient;
import com.example.userservice.client.AgreementClient;
import com.example.userservice.dto.AddressDto;
import com.example.userservice.dto.AgreementDto;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.models.User;
import com.example.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AddressClient addressClient;
    private final AgreementClient agreementClient;

    public UserResponse createUser(UserRequest userRequest) {
        final User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .phoneNumber(userRequest.getPhoneNumber())
                .mail(userRequest.getMail())
                .build();
        UserResponse userResponse = mapToUserResponse(userRepo.save(user));
        log.info("User {} is saved", user.getUserId());

        return userResponse;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public UserResponse getUserById(long id) {
        return userRepo.findById(id)
                .map(this::mapToUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    public UserResponse updateUser(UserResponse userRequest, long id) {
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

    private UserResponse mapToUserResponse(User user) {
        AddressDto address = null;
        List<AgreementDto> agreements = new ArrayList<>();

        try {
            address = addressClient.getAddressByUserId(user.getAddressId());
        } catch (Exception e) {
            log.warn("Could not fetch address for user {}: {}", user.getUserId(), e.getMessage());
        }

        try {
            agreements = agreementClient.getAgreementsByUserId(user.getUserId());
        } catch (Exception e) {
            log.warn("Could not fetch agreements for user {}: {}", user.getUserId(), e.getMessage());
        }

        return UserResponse.builder()
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
