package com.github.jomardev25.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.github.jomardev25.constant.Permission.*;

@RequiredArgsConstructor
public enum Role {

    AUTHOR(
        Set.of(READ_POST, CREATE_POST, UPDATE_POST, DELETE_POST, PUBLISH_POST)
    ),
    EDITOR(
        Set.of(READ_POST, READ_ALL_POST, CREATE_POST, UPDATE_POST, DELETE_POST, PUBLISH_POST)
    ),
    ADMIN(
        Set.of(READ_POST, READ_ALL_POST, CREATE_POST, UPDATE_POST, DELETE_POST, PUBLISH_POST, READ_USER, CREATE_USER, UPDATE_USER, DELETE_USER)
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.getPermissions()
            .stream()
            .forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName())));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
