package com.github.jomardev25.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jomardev25.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    
    Optional<Article> findBySlug(String slug);
}
