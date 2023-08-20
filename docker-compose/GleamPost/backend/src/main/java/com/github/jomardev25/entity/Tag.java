package com.github.jomardev25.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @JoinTable(
        name = "article_tag", 
        joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    @ManyToMany
    private Set<Article> articles = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
