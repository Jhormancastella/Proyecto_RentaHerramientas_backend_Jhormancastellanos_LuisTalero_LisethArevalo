package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SaveUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(User user) {
        if (user.getId() == null && userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }
        
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if (user.getId() != null) {
            User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            user.setPassword(existingUser.getPassword());
        }
        
        return userRepository.save(user);
    }
}
