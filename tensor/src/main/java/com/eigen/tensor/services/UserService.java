package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUserById(UUID authorId);
    List<Post> getAllPost(UUID authorId);
    User createUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);

}
