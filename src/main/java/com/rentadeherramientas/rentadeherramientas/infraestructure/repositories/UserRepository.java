package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRoleName(RoleName roleName);
    List<User> findByRole(RoleName admin);
}