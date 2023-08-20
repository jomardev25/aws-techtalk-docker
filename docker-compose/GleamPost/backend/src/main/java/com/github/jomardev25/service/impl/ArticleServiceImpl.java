package com.github.jomardev25.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jomardev25.dto.ArticleResponseDto;
import com.github.jomardev25.dto.CreateArticleRequestDto;
import com.github.jomardev25.entity.Article;
import com.github.jomardev25.entity.Tag;
import com.github.jomardev25.entity.User;
import com.github.jomardev25.exception.ResourceNotFoundException;
import com.github.jomardev25.repository.ArticleRepository;
import com.github.jomardev25.repository.TagRepository;
import com.github.jomardev25.repository.UserRepository;
import com.github.jomardev25.service.ArticleService;
import com.github.jomardev25.service.FileStorageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<ArticleResponseDto> getFeaturedArticles() {
        List<Article> top6Articles = articleRepository.findFirst6ByOrderByPublishedAtDesc();
        return top6Articles
                .stream()
                .map(a -> modelMapper.map(a, ArticleResponseDto.class))
                .toList();
    }

    @Transactional
    @Override
    public ArticleResponseDto createArticle(String model, MultipartFile file) {
        try {
            CreateArticleRequestDto request = objectMapper.readValue(model, CreateArticleRequestDto.class);
            Set<Tag> tags = request.getTags().stream().map(t -> tagRepository.save(new Tag(t))).collect(Collectors.toSet());
            
            User author = userRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getAuthorId()));

            

            String imgUrl = fileStorageService.store(file);
            ServletUriComponentsBuilder.fromCurrentContextPath().path(imgUrl).toUriString();

            Article article = Article.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .slug(request.getSlug())
                    .body(request.getBody())
                    .author(author)
                    .imageUrl(imgUrl)
                    .tags(tags)
                    .build();

            return modelMapper.map(articleRepository.save(article), ArticleResponseDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
