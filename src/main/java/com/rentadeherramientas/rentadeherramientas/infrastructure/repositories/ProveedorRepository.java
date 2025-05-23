package com.rentadeherramientas.rentadeherramientas.infrastructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Proveedor;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByNombre(String nombre);

    // Buscar proveedores por email
    Optional<Proveedor> findByEmail(String email);

    // Buscar todos los proveedores activos
    List<Proveedor> findByActivoTrue();

    // Buscar proveedores por ciudad
    List<Proveedor> findByCiudad(String ciudad);

    boolean existsByNombre(String nombre);
}
