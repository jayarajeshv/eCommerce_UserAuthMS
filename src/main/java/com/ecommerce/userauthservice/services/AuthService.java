package com.ecommerce.userauthservice.services;

import com.ecommerce.userauthservice.exceptions.IncorrectPasswordException;
import com.ecommerce.userauthservice.exceptions.PasswordLengthRestrictionsNotMet;
import com.ecommerce.userauthservice.exceptions.UserAlreadyExist;
import com.ecommerce.userauthservice.exceptions.UserNotFoundException;
import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.User;
import com.ecommerce.userauthservice.repositories.RoleRepository;
import com.ecommerce.userauthservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        newUuser.setPassword(password);
        List<Role> roles = new ArrayList<>();
        Role newRole = new Role();
        if (role != null) {
            newRole.setRoleName(role);
        } else {
            newRole.setRoleName("USER");
        }
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
    public User login(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (user.getPassword() != null && user.getPassword().equals(password)) { // Enable BCrypt
            return user;
        } else {
            throw new IncorrectPasswordException("Incorrect Password");
        }
    }
}
