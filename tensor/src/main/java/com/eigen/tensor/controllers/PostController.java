package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.PostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import com.eigen.tensor.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<PostResponseDto> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(postService.getPostBySlug(slug));
    }


    @PutMapping("{slug}")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostRequestDto request, @RequestBody Post post){
        return ResponseEntity.ok(postService.updatePost(request.getPostId(), post));
    }

    @PostMapping("/publish/{slug}")
    public ResponseEntity<PostResponseDto> publishPost(@PathVariable String slug){
        return ResponseEntity.ok(postService.publishPost(slug));
    }

    @DeleteMapping("/{slug}")
    public void deletePost(@RequestBody PostRequestDto request){
        postService.deletePost(request.getPostId(), request.getAuthorId());
    }
}
