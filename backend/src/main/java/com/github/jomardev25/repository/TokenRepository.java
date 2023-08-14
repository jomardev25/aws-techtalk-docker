package com.github.jomardev25.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.jomardev25.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
        select t from Token t inner join User u\s
        on t.user.id = u.id\s
        where u.id = :id and (t.expired = false and t.revoked = false)\s
        """
    )
    List<Token> findAllValidTokens(Long id);

    Optional<Token> findByToken(String token);
}
