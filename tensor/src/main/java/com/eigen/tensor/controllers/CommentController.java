package com.eigen.tensor.controllers;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.dto.AddCommentRequestDto;
import com.eigen.tensor.domain.entities.dto.CommentResponseDto;
import com.eigen.tensor.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts/{slug}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable UUID commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostSlug(@PathVariable String slug){
        return ResponseEntity.ok(commentService.getCommentsByPostSlug(slug));
    }

    @PostMapping
    public CommentResponseDto addComment(@RequestBody AddCommentRequestDto request){
        return commentService.addComment(request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable UUID commentId){
        commentService.deleteComment(commentId);
    }


}
