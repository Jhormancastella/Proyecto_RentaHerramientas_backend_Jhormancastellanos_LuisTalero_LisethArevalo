package com.rentadeherramientas.rentadeherramientas.repository;

import com.rentaherramientas.model.Herramienta;
import com.rentaherramientas.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    List<Herramienta> findByProveedor(Proveedor proveedor);
    List<Herramienta> findByEstado(String estado);
    List<Herramienta> findByCategoria(String categoria);
}
