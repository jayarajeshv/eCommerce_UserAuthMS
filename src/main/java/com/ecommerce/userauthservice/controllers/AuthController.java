package com.ecommerce.userauthservice.controllers;

import com.ecommerce.userauthservice.dtos.UserSignUpDto;
import com.ecommerce.userauthservice.dtos.UserSignUpResponseDto;
import com.ecommerce.userauthservice.exceptions.PasswordLengthRestrictionsNotMet;
import com.ecommerce.userauthservice.exceptions.UserAlreadyExist;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.User;
import com.ecommerce.userauthservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserSignUpResponseDto> signUp(@RequestBody UserSignUpDto userSignUpDto) throws PasswordLengthRestrictionsNotMet, UserAlreadyExist {
        User user = authService.signUp(userSignUpDto.getUsername(), userSignUpDto.getPassword(), userSignUpDto.getEmail(), userSignUpDto.getRole());
        return new ResponseEntity<>(fromUser(user), HttpStatus.CREATED);
    }

    private UserSignUpResponseDto fromUser(User user) {
        UserSignUpResponseDto Dto = new UserSignUpResponseDto();
        Dto.setId(user.getId());
        Dto.setUsername(user.getUsername());
        Dto.setEmail(user.getEmail());
        StringBuilder sb = new StringBuilder();
        for (Role role : user.getRoles()) {
            sb.append(role.getRoleName()).append(" ");
        }
        Dto.setRoles(sb.toString().trim());
        return Dto;
    }
}
