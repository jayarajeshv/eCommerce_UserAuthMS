package com.ecommerce.userauthservice.repositories;

import com.ecommerce.userauthservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
