package com.eigen.tensor.services.impl;


import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
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
    public Post createPost(String title, String content, UUID authorId) {
        return postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .status(PostStatus.DRAFT)
                .author(userService.getUserById(authorId))
                .build());
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Override
    public Post getPostBySlug(String slug){
        return postRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Post not found with slug: " + slug));
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(UUID userId) {
        return postRepository.findByAuthorId(userId);
    }

    @Override
    public Post updatePost(String slug, Post post) {
        Post oldPost = getPostBySlug(slug);
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
    public void deletePost(String slug, UUID userId) {
        Post post = getPostBySlug(slug);
        User user = userService.getUserById(userId);

        if(userId.equals(post.getAuthor().getId()) || user.getRole() == Role.ADMIN){
            postRepository.deleteBySlug(slug);
        } else {
            throw new RuntimeException("You don't have permission to delete this post");
        }
    }
}
