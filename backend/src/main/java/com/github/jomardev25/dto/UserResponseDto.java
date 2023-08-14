package com.github.jomardev25.dto;

import com.github.jomardev25.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String imageUrl;
    private String bio;
    private Role role;
}
