package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.dto.AddCommentRequest;
import com.eigen.tensor.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable UUID commentId){
        return commentService.getCommentById(commentId);
    }

    @GetMapping
    public List<Comment> getCommentsByPostId(@PathVariable UUID postId){
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public Comment addComment(@PathVariable UUID postId, @RequestBody AddCommentRequest request){
        return commentService.addComment(request.getAuthorId(), postId, request.getContent());
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    public void deleteComment(@PathVariable UUID commentId, @PathVariable UUID userId){
        commentService.deleteComment(commentId, userId);
    }


}
