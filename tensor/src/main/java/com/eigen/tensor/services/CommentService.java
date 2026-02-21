package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.dto.AddCommentRequestDto;
import com.eigen.tensor.domain.entities.dto.CommentResponseDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentResponseDto addComment(AddCommentRequestDto request);
    CommentResponseDto getCommentById(UUID commentId);
    List<CommentResponseDto> getCommentsByPostSlug(String slug);
    void deleteComment(UUID commentID);
}
