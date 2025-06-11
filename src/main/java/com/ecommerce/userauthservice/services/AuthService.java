package com.ecommerce.userauthservice.services;

import com.ecommerce.userauthservice.exceptions.*;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.Token;
import com.ecommerce.userauthservice.models.User;
import com.ecommerce.userauthservice.repositories.RoleRepository;
import com.ecommerce.userauthservice.repositories.TokenRepository;
import com.ecommerce.userauthservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(String username, String password, String email, String role) throws UserAlreadyExist, PasswordLengthRestrictionsNotMet {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExist("User Already Exist");
        }
        User newUuser = new User();
        newUuser.setEmail(email);
        newUuser.setUsername(username);
        if (password.trim().length() < 6) {
            throw new PasswordLengthRestrictionsNotMet("Password Length Restrictions Not Met");
        }
        newUuser.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        Role newRole = new Role();
        newRole.setRoleName(role != null ? role : "USER");
        roles.add(newRole);
        newUuser.setRoles(roles);
        roleRepository.saveAll(roles);
        return userRepository.save(newUuser);
    }

    @Override
    public User addUserRole(String email, Role role) throws UserNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        List<Role> roles = user.getRoles();
        roles.add(role);
        roleRepository.saveAll(roles);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (user.getPassword() != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            Optional<Token> optionalToken = tokenRepository.findTokenByUser(user);
            Token token;
            token = optionalToken.orElseGet(Token::new);
            token.setUser(user);
            token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date expiryDate = calendar.getTime();
            token.setExpiresAt(expiryDate);
            tokenRepository.save(token);
            return token;
        } else {
            throw new IncorrectPasswordException("Incorrect Password");
        }
    }

    public User validateToken(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiresAtAfter(tokenValue, new Date());
        if (optionalToken.isEmpty()) {
//            throw new InvalidTokenException("Invalid Token");
            return null; // For compatibility with the previous code, returning null instead of throwing an exception
        }
        return optionalToken.get().getUser();
    }
}
