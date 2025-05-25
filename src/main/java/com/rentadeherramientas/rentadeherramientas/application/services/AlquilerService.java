package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Alquiler;
import com.rentadeherramientas.rentadeherramientas.dto.request.AlquilerRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AlquilerResponse;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    List<Alquiler> findAll();
    Optional<Alquiler> findById(Long id);
    Alquiler save(Alquiler alquiler);
    void deleteById(Long id);
    Object obtenerPorEstado(String estado);
    Object obtenerTodos();
    AlquilerResponse crearAlquiler(AlquilerRequest request);
}
