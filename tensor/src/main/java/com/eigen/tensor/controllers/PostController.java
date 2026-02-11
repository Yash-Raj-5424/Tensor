package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/author/{authorId}")
    public Post createPost(@PathVariable UUID authorId, @RequestBody Post post) {
        return postService.createPost(authorId, post);
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable UUID id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @PostMapping("/publish/{id}")
    public Post publishPost(@PathVariable UUID id){
        return postService.publishPost(postService.getPostById(id));
    }

    @DeleteMapping("{postId}/user/{userId}")
    public void deletePost(@PathVariable UUID postId, @PathVariable UUID userId){
        postService.deletePost(postId, userId);
    }
}
