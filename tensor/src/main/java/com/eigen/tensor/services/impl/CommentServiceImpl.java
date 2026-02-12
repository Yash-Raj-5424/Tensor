package com.eigen.tensor.services.impl;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.domain.entities.enums.Role;
import com.eigen.tensor.repositories.CommentRepository;
import com.eigen.tensor.services.CommentService;
import com.eigen.tensor.services.PostService;
import com.eigen.tensor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;


    @Override
    public Comment addComment(UUID authorId, UUID postId, String content) {
        User author = userService.getUserById(authorId);
        Post post = postService.getPostById(postId);

        Comment comment = new Comment();

        comment.setContent(content);
        comment.setAuthor(author);
        comment.setPost(post);

        return commentRepository.save(comment);

    }

    @Override
    public Comment getCommentById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
    }

    @Override
    public List<Comment> getCommentsByPostId(UUID postId) {
        Post post = postService.getPostById(postId);
        return commentRepository.findByPostId(postId);
    }



    @Override
    public void deleteComment(UUID commentID, UUID userId) {
        Comment comment = getCommentById(commentID);
        Role userRole = userService.getUserById(userId).getRole();
        Post post = postService.getPostById(comment.getPost().getId());

        if(userId.equals(comment.getAuthor().getId()) || userId.equals(post.getAuthor().getId()) ||
                userRole == Role.ADMIN){
            commentRepository.deleteById(commentID);
        } else {
            throw new RuntimeException("You don't have permission to delete this comment");
        }

    }
}
