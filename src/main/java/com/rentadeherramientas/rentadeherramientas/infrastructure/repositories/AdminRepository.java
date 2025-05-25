package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByUsername(String username);
}