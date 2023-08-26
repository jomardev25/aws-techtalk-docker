package com.github.jomardev25.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.jomardev25.dto.ArticleResponseDto;
import com.github.jomardev25.dto.PaginatedArticleResponseDto;
import com.github.jomardev25.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<PaginatedArticleResponseDto> getAll(
        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return ResponseEntity.ok().body(articleService.getAllArticles(page - 1, size, sortBy, sortDir));
    }

    @GetMapping("/features")
    public ResponseEntity<List<ArticleResponseDto>> getFeaturedArticles() {
        return ResponseEntity.ok().body(articleService.getFeaturedArticles());
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestParam("model") String model, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(articleService.createArticle(model, file), HttpStatus.CREATED);
    }
}
