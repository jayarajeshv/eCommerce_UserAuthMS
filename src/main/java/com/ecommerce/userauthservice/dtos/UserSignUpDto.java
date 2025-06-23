package com.ecommerce.userauthservice.dtos;

import lombok.Data;

@Data
public class UserSignUpDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
