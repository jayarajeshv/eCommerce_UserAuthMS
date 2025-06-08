package com.ecommerce.userauthservice.repositories;

import com.ecommerce.userauthservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
