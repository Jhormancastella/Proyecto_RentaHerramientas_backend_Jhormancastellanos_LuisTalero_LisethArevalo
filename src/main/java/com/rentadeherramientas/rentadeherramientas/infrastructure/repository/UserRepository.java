package com.rentadeherramientas.rentadeherramientas.infrastructure.repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Add this method:
    List<User> findByRoles_Name(String roleName);
}