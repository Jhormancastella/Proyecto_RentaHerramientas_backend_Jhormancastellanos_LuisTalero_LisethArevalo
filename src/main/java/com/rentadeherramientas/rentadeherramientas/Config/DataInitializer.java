package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.RoleRepository;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.UserRepository;

import jakarta.transaction.Transactional;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Role;
import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // 1. Crear roles si no existen
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
            .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_ADMIN)));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_USER)));

        // 2. Crear usuario admin
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@rentadeherramientas.com");
            admin.setFirstName("Admin");
            admin.setLastName("System");
            admin.setPhone("1234567890");
            admin.setAddress("System Address");
            admin.setActive(true);
            
            // Asignar rol persistido
            admin.getRoles().add(adminRole);
            
            userRepository.save(admin);
            System.out.println("Usuario administrador creado exitosamente");
        }
    }
}