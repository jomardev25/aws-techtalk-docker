package com.github.jomardev25.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jomardev25.dto.UserListResponseDto;
import com.github.jomardev25.dto.UserRequestDto;
import com.github.jomardev25.dto.UserResponseDto;
import com.github.jomardev25.entity.User;
import com.github.jomardev25.repository.UserRepository;
import com.github.jomardev25.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userReporitory;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserListResponseDto getAllUsers(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<User> users = userReporitory.findAll(pageable);

        List<UserResponseDto> data = users.stream()
                                        .map(u -> modelMapper.map(u, UserResponseDto.class))
                                        .toList();
        return UserListResponseDto
                .builder()
                .page(page)
                .perPage(users.getSize())
                .totalPages(users.getTotalPages())
                .totalRecord(users.getTotalElements())
                .first(users.isFirst())
                .last(users.isLast())
                .data(data)
                .build();       
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        String password = passwordEncoder.encode(request.getPassword()); 
        User user = User.builder()
                        .username(request.getUsername())
                        .password(password)
                        .email(request.getEmail())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .imageUrl(request.getImageUrl())
                        .bio(request.getBio())
                        .role(request.getRole())
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .build();

        User savedUser = userReporitory.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = userReporitory.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user, UserResponseDto.class);
    }

}
