package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.PostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDto createPost(String title, String content, UUID authorId);
    String generateSlug(String title);
    Post getPostById(UUID id);  // for private use not exposed endpoint
    PostResponseDto getPostBySlug(String slug);
    Post updatePost(UUID id, Post post);
    Post publishPost(Post post);
    void deletePost(UUID postId, UUID userId);

    PostResponseDto mapToDto(Post post);

    List<PostResponseDto> getPostsByUserId(UUID userId);
}
