package controllers;

import com.eigen.tensor.domain.entities.Comment;
import com.eigen.tensor.domain.entities.User;
import com.eigen.tensor.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentServiceImpl commentService;

    @GetMapping("/id/{postId}")
    List<Comment> getAllComments(@PathVariable UUID postId){
        return commentService.getAllComments(postId);
    }

    @GetMapping
    Comment getCommentById(@PathVariable UUID id){
        return commentService.getCommentById(id);
    }

    @PostMapping("/id/{postId}")
    Comment createComment(@PathVariable UUID postId, @RequestBody String content, User user){
        return commentService.createComment(postId, content, user);
    }

    @PutMapping("/id/{cId}")
    Comment updateComment(@PathVariable UUID cid, @RequestBody String content, User user){
        return commentService.updateComment(cid, content, user);
    }

    @DeleteMapping("/id/{cid}")
    void deleteComment(@PathVariable UUID cid, @RequestBody User user){
        commentService.deleteComment(cid, user);
    }
}
