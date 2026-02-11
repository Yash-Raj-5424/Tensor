package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Post createPost(String title, String content, UUID authorId);
    Post getPostById(UUID id);
    List<Post> getAllPost();
    Post updatePost(UUID id, Post post);
    Post publishPost(Post post);
    void deletePost(UUID id, UUID userId);


    List<Post> getPostsByUserId(UUID userId);
}
