package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.PostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDto createPost(PostRequestDto request);
    String generateSlug(String title);
    Post getPostById(UUID id);  // for private use not exposed to any endpoint
    PostResponseDto getPostBySlug(String slug);
    PostResponseDto updatePost(UUID id, Post post);
    PostResponseDto publishPost(String slug);
    void deletePost(UUID postId, UUID userId);

    PostResponseDto mapToDto(Post post);

    List<PostResponseDto> getPostsByUserId(UUID userId);
}
