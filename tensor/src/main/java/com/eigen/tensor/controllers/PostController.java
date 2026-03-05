package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.dto.CreatePostRequestDto;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import com.eigen.tensor.domain.entities.dto.UpdatePostRequestDto;
import com.eigen.tensor.services.PostService;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody CreatePostRequestDto request) {
        PostResponseDto response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Post> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(postService.getPostBySlug(slug));
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("{slug}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable String slug,
                                                      @RequestBody UpdatePostRequestDto request) {

        return ResponseEntity.ok(postService.updatePost(slug, request));
    }

    @PostMapping("/{slug}/publish")
    public ResponseEntity<PostResponseDto> publishPost(@PathVariable String slug){
        return ResponseEntity.ok(postService.publishPost(slug));
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> deletePost(@PathVariable String slug ){

        User currentUser = userService.getCurrentUser();
        Post post = postService.getPostBySlug(slug);
        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        postService.deletePost(slug);
        return ResponseEntity.noContent().build();
    }
}
