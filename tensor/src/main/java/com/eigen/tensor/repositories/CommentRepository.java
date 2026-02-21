package com.eigen.tensor.repositories;

import com.eigen.tensor.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByPostSlug(String slug);
}
