package com.eigen.tensor.services.impl;


import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.enums.Role;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.UserRepository;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
