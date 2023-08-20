package com.github.jomardev25.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.jomardev25.dto.ArticleResponseDto;
import com.github.jomardev25.service.ArticleService;
import com.github.jomardev25.service.impl.FileStorageServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final FileStorageServiceImpl storageService;

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok().body("Test");
    }

    @GetMapping("/features")
    public ResponseEntity<List<ArticleResponseDto>> getFeaturedArticles() {
        return ResponseEntity.ok().body(articleService.getFeaturedArticles());
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestParam("model") String model,
            @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(articleService.createArticle(model, file), HttpStatus.CREATED);
    }
}
