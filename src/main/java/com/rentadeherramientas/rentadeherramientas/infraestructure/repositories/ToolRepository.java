package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import java.util.List;

import javax.tools.Tool;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByProveedorId(Long proveedorId);
}