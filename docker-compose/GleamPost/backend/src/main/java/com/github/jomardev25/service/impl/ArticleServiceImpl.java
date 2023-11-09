package com.github.jomardev25.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jomardev25.dto.ArticleResponseDto;
import com.github.jomardev25.dto.CreateArticleRequestDto;
import com.github.jomardev25.dto.PaginatedArticleResponseDto;
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

        @Value("${application.file.base-url}")
        private String baseUrl;
        private final ArticleRepository articleRepository;
        private final TagRepository tagRepository;
        private final UserRepository userRepository;
        private final FileStorageService fileStorageService;
        private final ModelMapper modelMapper;
        private final ObjectMapper objectMapper;

        @Override
        public PaginatedArticleResponseDto getAllArticles(int page, int size, String sortBy, String sortDir) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(page, size, sort);
                Page<Article> articles = articleRepository.findAll(pageable);
                List<ArticleResponseDto> content = articles.stream()
                        .map(a -> modelMapper.map(a, ArticleResponseDto.class))
                        .toList();

                return PaginatedArticleResponseDto.builder()
                        .pageNumber(articles.getNumber())
                        .pageSize(articles.getSize())
                        .totalElements(articles.getTotalElements())
                        .totalPages(articles.getTotalPages())
                        .first(articles.isFirst())
                        .last(articles.isLast())
                        .articles(content)
                        .build();
        }

        @Override
        public List<ArticleResponseDto> getFeaturedArticles() {
                List<Article> top6Articles = articleRepository.findFirst6ByOrderByPublishedAtDesc();
                return top6Articles
                        .stream()
                        .map(a -> ArticleResponseDto.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .body(a.getBody())
                        .slug(a.getSlug())
                        .imageUrl(baseUrl + "/uploads/" + a.getImageUrl())
                        .build())
                        .toList();
        }

        @Transactional
        @Override
        public ArticleResponseDto createArticle(String model, MultipartFile file) {
                try {
                        CreateArticleRequestDto request = objectMapper.readValue(model, CreateArticleRequestDto.class);
                        final String username;

                        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                        if (principal instanceof UserDetails) {
                                username = ((UserDetails) principal).getUsername();
                        } else {
                                username = principal.toString();
                        }

                        User author = userRepository.findByUsername(username)
                                        .orElseThrow(() -> new ResourceNotFoundException("User", "username",
                                                        username));

                        String imgUrl = fileStorageService.store(file);
                        ServletUriComponentsBuilder.fromCurrentContextPath().path(imgUrl).toUriString();

                        Article article = Article.builder()
                                        .title(request.getTitle())
                                        .description(request.getDescription())
                                        .slug(request.getSlug())
                                        .body(request.getBody())
                                        .author(author)
                                        .imageUrl(imgUrl)
                                        .build();

                        if(!request.getTags().isEmpty()) {
                                Set<Tag> tags = request.getTags().stream().map(t -> tagRepository.save(new Tag(t)))
                                        .collect(Collectors.toSet());
                                article.setTags(tags);
                        }

                        return modelMapper.map(articleRepository.save(article), ArticleResponseDto.class);
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }
                return null;
        }

}
