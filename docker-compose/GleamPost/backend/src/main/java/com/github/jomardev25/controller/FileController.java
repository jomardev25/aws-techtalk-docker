package com.github.jomardev25.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.jomardev25.service.FileStorageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileStorageService fileStorageService;

    @GetMapping(value = "/uploads/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(fileStorageService.load(filename)));
    }
}
