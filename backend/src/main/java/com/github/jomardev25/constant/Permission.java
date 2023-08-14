package com.github.jomardev25.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    
    READ_POST("read-post"),
    CREATE_POST("create-post"),
    READ_ALL_POST("read-all-post"),
    UPDATE_POST("update-post"),
    DELETE_POST("delete-post"),
    PUBLISH_POST("publish-post"),
    READ_USER("read-user"),
    CREATE_USER("create-user"),
    UPDATE_USER("update-user"),
    DELETE_USER("delete-user");
    
    @Getter
    private final String name;
}
