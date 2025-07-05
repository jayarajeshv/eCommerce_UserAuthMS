package com.ecommerce.userauthservice.utils;

import com.ecommerce.userauthservice.models.Role;
import com.ecommerce.userauthservice.models.User;
import com.ecommerce.userauthservice.repositories.RoleRepository;
import com.ecommerce.userauthservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            List<User> user = userRepository.findAll();
            if (user.isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setEmail("admin");
                adminUser.setPassword(passwordEncoder.encode("password"));
                Role role = new Role();
                role.setRoleName("ADMIN");
                roleRepository.save(role);
                List<Role> roles = new ArrayList<>();
                roles.add(role);
                adminUser.setRoles(roles);
                userRepository.save(adminUser);
            }
        };
    }
}