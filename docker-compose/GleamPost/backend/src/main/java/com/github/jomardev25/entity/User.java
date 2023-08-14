package com.github.jomardev25.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.jomardev25.constant.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "is_credentials_Non_expired")
    private boolean credentialsNonExpired;

    @Builder.Default
    @Column(name = "is_enabled")
    private boolean enabled = true;

    @Transient
    @Builder.Default
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Article> articles = new ArrayList<>();

    @Builder.Default
    @JoinTable(name = "user_following", joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> following = new HashSet<>();

    @Builder.Default
    @JoinTable(name = "user_following", joinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> followers = new HashSet<>();

    @Transient
    @Builder.Default
    @JoinTable(name = "favorite_articles", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    @ManyToMany
    private Set<Article> favoriteArticles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private void addArticle(Article article) {
        this.articles.add(article);
        article.setAuthor(this);
    }

    /* private void addFavoriteArticle(Article article) {
        this.favoriteArticles.add(article);
        article.getUsers().add(this);
    } */

    private void addFollower(User follower) {
        this.followers.add(follower);
    }

    private void addFollowing(User following) {
        this.following.add(following);
    }
    
}
