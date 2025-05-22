package com.rentadeherramientas.rentadeherramientas.infraestructure.repositories;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Add custom query methods here if needed
}
