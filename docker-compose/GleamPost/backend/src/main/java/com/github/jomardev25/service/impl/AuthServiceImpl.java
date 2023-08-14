package com.github.jomardev25.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.jomardev25.constant.Role;
import com.github.jomardev25.constant.TokenType;
import com.github.jomardev25.dto.AuthenticationRequestDto;
import com.github.jomardev25.dto.AuthenticationResponseDto;
import com.github.jomardev25.dto.RegisterRequestDto;
import com.github.jomardev25.dto.UserResponseDto;
import com.github.jomardev25.entity.Token;
import com.github.jomardev25.entity.User;
import com.github.jomardev25.repository.TokenRepository;
import com.github.jomardev25.repository.UserRepository;
import com.github.jomardev25.service.AuthService;
import com.github.jomardev25.service.JwtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        String password = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .password(password)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .imageUrl(request.getImageUrl())
                .bio(request.getBio())
                .role(Role.AUTHOR)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, token);

        return AuthenticationResponseDto.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
