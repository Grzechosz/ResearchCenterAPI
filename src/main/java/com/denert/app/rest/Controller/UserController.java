package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.Project;
import com.denert.app.rest.Models.TestResults;
import com.denert.app.rest.Models.User;
import com.denert.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

/*    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }*/
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable long id) {
        return userRepo.findById(id).get();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setMail(user.getMail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPermissions(user.getPermissions());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setAddress(user.getAddress());
        userRepo.save(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Deleted user with id: "+id;
    }
}
