package com.eigen.tensor.domain.entities;

import com.eigen.tensor.domain.entities.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Size(min = 3, max = 20)
    private String username;
    private String password;
    private List<Post> posts;

    @Enumerated
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
