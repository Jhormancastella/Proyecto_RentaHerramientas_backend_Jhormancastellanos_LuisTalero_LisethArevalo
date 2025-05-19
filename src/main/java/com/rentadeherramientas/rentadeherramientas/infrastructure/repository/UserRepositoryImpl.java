package com.rentadeherramientas.rentadeherramientas.infrastructure.repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Long>, UserRepository {
    // La implementación de los métodos es proporcionada automáticamente por Spring Data JPA
}