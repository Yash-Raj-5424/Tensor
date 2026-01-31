package com.eigen.tensor.services.impl;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.CommentRepository;
import com.eigen.tensor.services.CommentService;
import com.eigen.tensor.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Override
    public List<Comment> getAllComments(UUID postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getCommentById(UUID id){
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment Not Found!"));
    }

    @Override
    public Comment createComment(UUID postId, String content, User user) {
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(user);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(UUID id, String content, User user) {
        Comment comment = this.getCommentById(id);
        if(user != null && comment.getAuthor().equals(user)) {
            comment.setContent(content);
            return comment;
        }
        return null;
    }

    @Override
    public void deleteComment(UUID id, User user) {
        Comment comment = this.getCommentById(id);
        if(user != null && user.equals(comment.getAuthor())){
            commentRepository.deleteById(id);
        }
    }
}
