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
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String imageUrl;
    private String bio;
    private Role role;
}
