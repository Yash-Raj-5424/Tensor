package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/id/{userId}")
    User getUserById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}/posts")
    List<Post> getAllPosts(@PathVariable UUID id){
        return userService.getAllPost(id);
    }

    @PostMapping
    User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/id/{userId}")
    User updateUser(@PathVariable UUID userId, @RequestBody User user){
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/id/{userId}")
    void deleteMapping(@PathVariable UUID userId){
        userService.deleteUser(userId);
    }
}
