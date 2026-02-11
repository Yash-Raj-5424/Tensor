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
    public Post createPost(UUID authorId, Post post) {
        User author = userService.getUserById(authorId);
        post.setAuthor(author);
        post.setStatus(PostStatus.DRAFT);

        return postRepository.save(post);
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByAuthorname(String username) {
        try{
            User author = userService.getUserByUsername(username);
        }catch (RuntimeException e){
            throw new RuntimeException("Author not found with username: " + username);
        }
        return postRepository.findByAuthorname(username);
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
    public void deletePost(UUID id, UUID userId) {
        Post post = getPostById(id);
        User user = userService.getUserById(userId);

        if(userId.equals(post.getAuthor().getId()) || user.getRole() == Role.ADMIN){
            postRepository.deleteById(id);
        } else {
            throw new RuntimeException("You don't have permission to delete this post");
        }
    }
}
