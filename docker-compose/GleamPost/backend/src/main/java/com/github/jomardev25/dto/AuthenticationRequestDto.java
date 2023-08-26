package com.github.jomardev25.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequestDto {

    @NotNull
    @NotEmpty
    @Email
    private String username;

    @NotNull
    @NotEmpty
    private String password;
}
