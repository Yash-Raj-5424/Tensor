package com.eigen.tensor.domain.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private String slug;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
