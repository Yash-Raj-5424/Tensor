package controllers;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.services.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @GetMapping
    List<Post> getPosts(){
        return postService.getAllPost();
    }

    @GetMapping("/id/{myId}")
    Post getPostById(@PathVariable UUID myId){
        return postService.getPostById(myId);
    }

    @PostMapping
    Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @PutMapping("/id/{myid}")
    Post updatePost(@PathVariable UUID myid, @RequestBody Post post){
        return postService.updatePost(myid, post);
    }

    @DeleteMapping("/id/{myId}")
    void deletePost(@PathVariable UUID myId){
        postService.deletePost(myId);
    }

}
