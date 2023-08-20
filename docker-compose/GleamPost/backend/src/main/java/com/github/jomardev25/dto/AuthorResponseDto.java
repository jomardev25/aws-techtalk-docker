package com.github.jomardev25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
