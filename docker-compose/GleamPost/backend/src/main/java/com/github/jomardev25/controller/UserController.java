package com.github.jomardev25.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jomardev25.dto.UserListResponseDto;
import com.github.jomardev25.dto.UserRequestDto;
import com.github.jomardev25.dto.UserResponseDto;
import com.github.jomardev25.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserListResponseDto> getAll(
        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "sort_by", defaultValue = "id", required = false) String sortBy,
        @RequestParam(name = "sort_dir", defaultValue = "asc", required = false) String sortDir
    ) {
        return ResponseEntity.ok().body(userService.getAllUsers(page, size, sortBy, sortDir));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto request) {
       return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    /*

    @PutMapping("{id}")
    public ResponseEntity<entityClassName> update(@RequestBody UserRequestDto request, @PathVariable("id") Long id) {
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") entityIdType id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    } */
}