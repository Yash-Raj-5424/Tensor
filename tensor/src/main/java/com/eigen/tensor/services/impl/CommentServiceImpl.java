package com.eigen.tensor.services.impl;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.dto.AddCommentRequestDto;
import com.eigen.tensor.domain.entities.dto.CommentResponseDto;
import com.eigen.tensor.domain.entities.enums.Role;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.CommentRepository;
import com.eigen.tensor.services.CommentService;
import com.eigen.tensor.services.PostService;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public CommentResponseDto mapToDto(Comment comment){
        return CommentResponseDto.builder()

                .content(comment.getContent())
                .authorUsername(comment.getAuthor().getUsername())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    @Override
    public CommentResponseDto addComment(AddCommentRequestDto request) {
        Comment comment = Comment.builder()
                .content(request.getContent())
                .build();
        Comment saved = commentRepository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public CommentResponseDto getCommentById(UUID commentId) {
        return commentRepository.findById(commentId)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostSlug(String slug) {
        return commentRepository.findByPostSlug(slug)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public void deleteComment(UUID commentID) {
        commentRepository.deleteById(commentID);
    }
}
