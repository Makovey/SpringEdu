package com.example.demo.controllers;

import com.example.demo.db.entity.User;
import com.example.demo.db.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("user")
public class UserController {

    UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    List<User> getUsers() {
        return StreamSupport
                .stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    User getUserById(@PathVariable("id") int id) {
        return findUserOrThrowNPE(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> createUser(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok("Created new user with name " + user.getName());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User updateUser(@PathVariable int id, @RequestBody User user) {
        return userRepo.findById(id)
                .map(old -> {
                    old.setName(user.getName());
                    old.setPassword(user.getPassword());
                    return userRepo.save(old);
                })
                .orElseThrow(() -> new NullPointerException("Not found user with id " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> deleteUser(@PathVariable int id) {
        userRepo.delete(findUserOrThrowNPE(id));
        return ResponseEntity.ok("Deleted user with id " + id);
    }

    private User findUserOrThrowNPE(int id) {
        return userRepo
                .findById(id)
                .orElseThrow(() -> new NullPointerException("Not found user with id " + id));
    }
}
