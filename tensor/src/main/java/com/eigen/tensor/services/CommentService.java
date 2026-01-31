package com.eigen.tensor.services;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> getAllComments(UUID postId);
    Comment getCommentById(UUID commentId);
    Comment createComment(UUID postId, String content, User user);
    Comment updateComment(UUID id, String content, User user);
    void deleteComment(UUID id, User user);
}
