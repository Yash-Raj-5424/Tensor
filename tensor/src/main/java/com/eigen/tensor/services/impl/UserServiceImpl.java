package com.eigen.tensor.services.impl;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.UserRepository;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }

    public List<Post> getAllPost(UUID autherId){
        User author = this.getUserById(autherId);
        return author.getPosts();
    }

    public User createUser(User user){
        user.setId(null);
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User newUser){
        User user = this.getUserById(id);
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());

        return user;
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }
}
