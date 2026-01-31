package com.eigen.tensor.services.impl;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.exception.ResourceNotFoundException;
import com.eigen.tensor.repositories.PostRepository;
import com.eigen.tensor.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not found"));
    }

    @Transactional
    @Override
    public Post createPost(Post post){
        post.setId(null);
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(UUID id, Post post){
        Post existing = this.getPostById(id);
        existing.setSlug(post.getSlug());
        existing.setContent(post.getContent());
        return postRepository.save(existing);
    }

    public void deletePost(UUID id){
        postRepository.deleteById(id);
    }

}
