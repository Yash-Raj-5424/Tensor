package com.eigen.tensor.domain.entities.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private UUID authorId;

}
