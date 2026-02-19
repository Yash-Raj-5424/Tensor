package com.eigen.tensor.domain.entities.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;


}
