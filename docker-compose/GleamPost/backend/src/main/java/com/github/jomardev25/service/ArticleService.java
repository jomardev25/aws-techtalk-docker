package com.github.jomardev25.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.jomardev25.dto.ArticleResponseDto;
import com.github.jomardev25.dto.PaginatedArticleResponseDto;

public interface ArticleService {

    PaginatedArticleResponseDto getAllArticles(int page, int size, String sortBy, String sortDir);

    List<ArticleResponseDto> getFeaturedArticles();

    ArticleResponseDto createArticle(String model, MultipartFile file);
}
