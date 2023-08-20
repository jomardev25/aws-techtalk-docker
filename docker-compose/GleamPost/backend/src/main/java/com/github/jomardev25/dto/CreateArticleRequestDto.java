package com.github.jomardev25.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateArticleRequestDto {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String slug;

    private String description;

    @NotNull
    @NotBlank
    private String body;

    @NotNull
    @NotBlank
    private Long authorId;

    @Builder.Default
    private Set<String> tags = new HashSet<>();

}
