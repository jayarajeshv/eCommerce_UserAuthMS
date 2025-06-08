package com.ecommerce.userauthservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "users")
public class User extends BaseModel {
    private String username;
    private String password;
    private String email;
    @ManyToMany
    private List<Role> roles;
}
