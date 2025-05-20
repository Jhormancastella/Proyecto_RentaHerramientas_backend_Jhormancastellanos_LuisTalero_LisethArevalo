package com.rentadeherramientas.rentadeherramientas.repository;

import com.rentaherramientas.model.Cliente;
import com.rentaherramientas.model.Herramienta;
import com.rentaherramientas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente(Cliente cliente);
    List<Reserva> findByHerramienta(Herramienta herramienta);
    List<Reserva> findByEstado(String estado);
    List<Reserva> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
}
