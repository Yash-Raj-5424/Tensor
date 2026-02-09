package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.User;

import java.util.UUID;

public interface UserService {

    User createUser(User user);
    User getUserById(UUID userId);
    User getUserByUsername(String username);
    void deleteUser(UUID userId);

}
