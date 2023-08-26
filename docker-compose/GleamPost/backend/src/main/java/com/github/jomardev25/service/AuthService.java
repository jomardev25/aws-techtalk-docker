package com.github.jomardev25.service;

import java.io.IOException;

import com.github.jomardev25.dto.AuthenticationRequestDto;
import com.github.jomardev25.dto.AuthenticationResponseDto;
import com.github.jomardev25.dto.RegisterRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    
    AuthenticationResponseDto register(RegisterRequestDto request);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
