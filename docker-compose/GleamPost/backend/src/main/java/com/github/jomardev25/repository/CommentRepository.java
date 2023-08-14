package com.github.jomardev25.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jomardev25.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
