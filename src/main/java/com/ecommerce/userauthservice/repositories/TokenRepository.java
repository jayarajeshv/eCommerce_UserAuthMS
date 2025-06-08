package com.ecommerce.userauthservice.repositories;

import com.ecommerce.userauthservice.models.Token;
import com.ecommerce.userauthservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByUser(User user);
}
