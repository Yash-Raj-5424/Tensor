package com.eigen.tensor.services.impl;


import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.dto.CreatePostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import com.eigen.tensor.domain.entities.dto.UpdatePostRequestDto;
import com.eigen.tensor.domain.entities.enums.PostStatus;
import com.eigen.tensor.domain.entities.enums.Role;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.PostRepository;
import com.eigen.tensor.services.PostService;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public String generateSlug(String title) {
        String baseSlug = title.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("[\\s+]", "-");

        String suffix = UUID.randomUUID().toString().substring(0, 6);
        return baseSlug + "-" + suffix;
    }

    @Override
    public PostResponseDto mapToDto(Post post) {
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .slug(post.getSlug())
                .status(post.getStatus().name())
                .authorName(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    @Override
    public PostResponseDto createPost(CreatePostRequestDto request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(userService.getUserById(request.getAuthorId()))
                .slug(generateSlug(request.getTitle()))
                .status(PostStatus.DRAFT)
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        Post saved = postRepository.save(post);
        return mapToDto(saved);
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    @Override
    public Post getPostBySlug(String slug){
        return postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with slug: " + slug));

    }


    @Override
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    public List<PostResponseDto> getPostsByUsername(String username) {
        UUID authorId = userService.getUserByUsername(username).getId();
        return postRepository.findByAuthorId(authorId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

//    @Override
//    public List<PostResponseDto> getMyPosts() {
//        // todo: get the currently authenticated user and return their posts
//    }

    @Override
    public PostResponseDto updatePost(String slug, UpdatePostRequestDto request) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with slug: " + slug));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(java.time.LocalDateTime.now());
        return mapToDto(postRepository.save(post));
    }

    @Override
    public PostResponseDto publishPost(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with slug: " + slug));
        post.setStatus(PostStatus.PUBLISHED);
        return mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePost(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with slug: " + slug));
        postRepository.delete(post);
    }
}
