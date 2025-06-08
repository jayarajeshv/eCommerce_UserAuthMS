package com.ecommerce.userauthservice.dtos;

import com.ecommerce.userauthservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserSignUpResponseDto {
    private Long id;
    private String username;
    private String email;
    private String roles;
}
