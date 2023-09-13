package com.denert.app.rest.service;


import com.denert.app.rest.dto.UserRequest;
import com.denert.app.rest.dto.UserResponse;
import com.denert.app.rest.exception.UserNotFoundException;
import com.denert.app.rest.models.User;
import com.denert.app.rest.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserResponse createUser(UserRequest userRequest) {
        final User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .mail(userRequest.getMail())
                .permissions(userRequest.getPermissions())
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

    public UserResponse updateUser(UserRequest userRequest, long id) {
        return userRepo.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userRequest.getName());
                    existingUser.setSurname(userRequest.getSurname());
                    existingUser.setPhoneNumber(userRequest.getPhoneNumber());
                    existingUser.setPassword(userRequest.getPassword());
                    existingUser.setMail(userRequest.getMail());
                    existingUser.setPermissions(userRequest.getPermissions());

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
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .mail(user.getMail())
                .permissions(user.getPermissions())
                .build();
    }
}
