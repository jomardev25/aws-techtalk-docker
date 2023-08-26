package com.github.jomardev25.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("published_at")
    private String publishedAt;
    private AuthorResponseDto author;
}
