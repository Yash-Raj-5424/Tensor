package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment addComment(UUID authorId, UUID postId, String content);
    Comment getCommentById(UUID commentId);
    List<Comment> getCommentsByPostId(UUID postId);
    void deleteComment(UUID commentID, UUID authorId);
}
