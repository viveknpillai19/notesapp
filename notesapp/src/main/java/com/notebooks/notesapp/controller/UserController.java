package com.notebooks.notesapp.controller;

import com.notebooks.notesapp.entity.User;
import com.notebooks.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}