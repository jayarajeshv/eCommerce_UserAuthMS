package com.ecommerce.userauthservice.services;

import com.ecommerce.userauthservice.exceptions.IncorrectPasswordException;
import com.ecommerce.userauthservice.exceptions.PasswordLengthRestrictionsNotMet;
import com.ecommerce.userauthservice.exceptions.UserAlreadyExist;
import com.ecommerce.userauthservice.exceptions.UserNotFoundException;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.User;

public interface IAuthService {
    User signUp(String username, String password, String email, String role) throws UserAlreadyExist, PasswordLengthRestrictionsNotMet;

    User addUserRole(String email, Role role) throws UserNotFoundException;

    User login(String email, String password) throws UserNotFoundException, IncorrectPasswordException;


}
