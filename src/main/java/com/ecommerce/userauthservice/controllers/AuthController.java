package com.ecommerce.userauthservice.controllers;

import com.ecommerce.userauthservice.dtos.LoginResponseDto;
import com.ecommerce.userauthservice.dtos.UserResponseDto;
import com.ecommerce.userauthservice.dtos.UserSignUpDto;
import com.ecommerce.userauthservice.exceptions.*;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.Token;
import com.ecommerce.userauthservice.models.User;
import com.ecommerce.userauthservice.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserSignUpDto userSignUpDto) throws PasswordLengthRestrictionsNotMet, UserAlreadyExist, JsonProcessingException, IncompleteUserDetailsException {
        User user = authService.signUp(userSignUpDto.getUsername(), userSignUpDto.getPassword(), userSignUpDto.getEmail(), userSignUpDto.getRole());
        return new ResponseEntity<>(fromUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestHeader("email") String email, @RequestHeader("password") String password) throws UserNotFoundException, IncorrectPasswordException {
        Token token = authService.login(email, password);
        return new ResponseEntity<>(loginResponse(token), HttpStatus.OK);
    }

    @GetMapping("/validate/{tokenValue}")
    public ResponseEntity<UserResponseDto> validateToken(@PathVariable String tokenValue) throws InvalidTokenException {
        User user = authService.validateToken(tokenValue);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(fromUser(user), HttpStatus.FOUND);
    }

    private UserResponseDto fromUser(User user) {
        UserResponseDto Dto = new UserResponseDto();
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

    private LoginResponseDto loginResponse(Token token) {
        LoginResponseDto Dto = new LoginResponseDto();
        Dto.setToken(token.getTokenValue());
        return Dto;
    }
}
