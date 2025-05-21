package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    boolean existsByName(String name);
    boolean existsBySerialNumber(String serialNumber);
    List<Tool> findByAvailableTrue();
}