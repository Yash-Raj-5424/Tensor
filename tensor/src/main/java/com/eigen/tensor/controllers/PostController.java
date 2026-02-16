package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.PostDto;
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

    @PostMapping
    public Post createPost(@RequestBody PostDto request) {
        return postService.createPost(request.getTitle(), request.getContent(), request.getAuthorId());
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @PutMapping("{slug}")
    public Post updatePost(@PathVariable String slug, @RequestBody Post post){
        return postService.updatePost(slug, post);
    }

    @PostMapping("/publish/{slug}")
    public Post publishPost(@PathVariable String slug){
        return postService.publishPost(postService.getPostBySlug(slug));
    }

    @DeleteMapping("/{slug}")
    public void deletePost(@PathVariable String slug, @RequestBody PostDto request){
        postService.deletePost(slug, request.getAuthorId());
    }
}
