package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Role;
import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.UserRepository;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crear roles si no existen
        createRoleIfNotFound(RoleName.ROLE_ADMIN);
        createRoleIfNotFound(RoleName.ROLE_USER);
        createRoleIfNotFound(RoleName.ROLE_PROVIDER);

        // Crear admin por defecto si no existe
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@rentaherramientas.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setActive(true);
            
            Set<Role> roles = new HashSet<>();
            roleRepository.findByName(RoleName.ROLE_ADMIN).ifPresent(roles::add);
            admin.setRoles(roles);
            
            userRepository.save(admin);
        }
    }

    private void createRoleIfNotFound(RoleName roleName) {
        if (!roleRepository.findByName(roleName).isPresent()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}