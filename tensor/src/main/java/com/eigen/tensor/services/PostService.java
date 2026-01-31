package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPost();
    Post getPostById(UUID id);
    Post createPost(Post post);
    Post updatePost(UUID id, Post post);
    void deletePost(UUID id);


}
