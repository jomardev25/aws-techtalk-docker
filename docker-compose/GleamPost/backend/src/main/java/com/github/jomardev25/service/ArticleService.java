package com.github.jomardev25.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.jomardev25.dto.ArticleResponseDto;

public interface ArticleService {
    List<ArticleResponseDto> getFeaturedArticles();

    ArticleResponseDto createArticle(String model, MultipartFile file);
}
