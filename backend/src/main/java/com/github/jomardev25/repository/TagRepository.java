package com.github.jomardev25.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jomardev25.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
