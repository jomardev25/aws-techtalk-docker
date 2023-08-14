package com.github.jomardev25.service;

import com.github.jomardev25.dto.UserListResponseDto;
import com.github.jomardev25.dto.UserRequestDto;
import com.github.jomardev25.dto.UserResponseDto;

public interface UserService {

    UserListResponseDto getAllUsers(int page, int size, String sortBy, String sortDir);
    
    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto getUserById(Long id);

}
