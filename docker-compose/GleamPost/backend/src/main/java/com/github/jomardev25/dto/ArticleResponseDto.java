package com.github.jomardev25.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String body;
    private String imageUrl;
    private LocalDateTime publishedAt;
    private AuthorResponseDto author;
}
