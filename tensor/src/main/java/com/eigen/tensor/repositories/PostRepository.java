package com.eigen.tensor.repositories;

import com.eigen.tensor.domain.entities.Post;
import com.eigen.tensor.domain.entities.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {


    List<PostResponseDto> findByAuthorId(UUID userId);
    Optional<Post> findBySlug(String slug);


}
