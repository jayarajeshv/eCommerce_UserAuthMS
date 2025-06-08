package com.ecommerce.userauthservice.dtos;

import com.ecommerce.userauthservice.models.Role;
import lombok.Data;

@Data
public class UserSignUpDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
