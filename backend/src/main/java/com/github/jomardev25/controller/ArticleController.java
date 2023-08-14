package com.github.jomardev25.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    
    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok().body("Test");
    }
}
