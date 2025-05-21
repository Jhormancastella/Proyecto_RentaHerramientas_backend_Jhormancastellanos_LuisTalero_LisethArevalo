package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

public interface UserRepositoryImpl extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<User> findByRoles_Name(String name);
}
