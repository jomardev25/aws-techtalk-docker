package com.github.jomardev25.service;

import com.github.jomardev25.dto.AuthenticationRequestDto;
import com.github.jomardev25.dto.AuthenticationResponseDto;
import com.github.jomardev25.dto.RegisterRequestDto;

public interface AuthService {
    
    AuthenticationResponseDto register(RegisterRequestDto request);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);

}
