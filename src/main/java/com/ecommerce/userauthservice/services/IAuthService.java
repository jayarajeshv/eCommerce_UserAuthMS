package com.ecommerce.userauthservice.services;

import com.ecommerce.userauthservice.exceptions.*;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.Token;
import com.ecommerce.userauthservice.models.User;

public interface IAuthService {
    User signUp(String username, String password, String email, String role) throws UserAlreadyExist, PasswordLengthRestrictionsNotMet;

    User addUserRole(String email, Role role) throws UserNotFoundException;

    Token login(String email, String password) throws UserNotFoundException, IncorrectPasswordException;

    User validateToken(String token) throws UserNotFoundException, InvalidTokenException;

}
