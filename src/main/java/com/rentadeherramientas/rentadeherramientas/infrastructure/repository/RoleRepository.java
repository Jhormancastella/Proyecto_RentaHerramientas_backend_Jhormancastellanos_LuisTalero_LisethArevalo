package com.rentadeherramientas.rentadeherramientas.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Role;
import com.rentadeherramientas.rentadeherramientas.domain.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}