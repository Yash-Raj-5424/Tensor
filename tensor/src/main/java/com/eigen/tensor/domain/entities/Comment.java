package com.eigen.tensor.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

}
