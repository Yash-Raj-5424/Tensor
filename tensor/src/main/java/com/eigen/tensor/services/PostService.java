package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.CreatePostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import com.eigen.tensor.domain.entities.dto.UpdatePostRequestDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDto createPost(CreatePostRequestDto request);
    String generateSlug(String title);
    Post getPostById(UUID id);  // for private use not exposed to any endpoint
    Post getPostBySlug(String slug);
    List<PostResponseDto> getPostsByUsername(String username);
    List<PostResponseDto> getAllPosts();
//    List<PostResponseDto> getMyPosts();
    PostResponseDto updatePost(String slug, UpdatePostRequestDto request);
    PostResponseDto publishPost(String slug);
    void deletePost(String slug);

    PostResponseDto mapToDto(Post post);

}
