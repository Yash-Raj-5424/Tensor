package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Post createPost(String title, String content, UUID authorId);
    String generateSlug(String title);
    Post getPostById(UUID id);  // for private use not exposed endpoint
    Post getPostBySlug(String slug);
    List<Post> getAllPost();
    Post updatePost(String slug, Post post);
    Post publishPost(Post post);
    void deletePost(String slug, UUID userId);


    List<Post> getPostsByUserId(UUID userId);
}
