package com.eigen.tensor.services.impl;


import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import com.eigen.tensor.domain.entities.enums.PostStatus;
import com.eigen.tensor.domain.entities.enums.Role;
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
                .replaceAll("[\\s]", "-");

        String suffix = UUID.randomUUID().toString().substring(0, 6);
        return baseSlug + "-" + suffix;
    }

    @Override
    public PostResponseDto mapToDto(Post post) {
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .authorName(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Override
    public PostResponseDto createPost(String title, String content, UUID authorId) {
        Post post =  postRepository.save(Post.builder()
                .title(title)
                .slug(generateSlug(title))
                .content(content)
                .status(PostStatus.DRAFT)
                .author(userService.getUserById(authorId))
                .build());

        return mapToDto(post);
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Override
    public PostResponseDto getPostBySlug(String slug){
        Post post = postRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Post not found with slug: " + slug));
        return mapToDto(post);
    }



    @Override
    public List<PostResponseDto> getPostsByUserId(UUID userId) {
        return postRepository.findByAuthorId(userId);
    }

    @Override
    public Post updatePost(UUID id, Post post) {
        Post oldPost = getPostById(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        return postRepository.save(oldPost);
    }

    @Override
    public Post publishPost(Post post) {
        post.setStatus(PostStatus.PUBLISHED);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(UUID postId, UUID userId) {
        Post post = getPostById(postId);
        User user = userService.getUserById(userId);

        if(userId.equals(post.getAuthor().getId()) || user.getRole() == Role.ADMIN){
            postRepository.deleteById(postId);
        } else {
            throw new RuntimeException("You don't have permission to delete this post");
        }
    }
}
