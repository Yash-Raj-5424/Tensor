package com.eigen.tensor.domain.entities.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private String content;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
