package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Alquiler;
import com.rentadeherramientas.rentadeherramientas.dto.request.AlquilerRequest;
import com.rentadeherramientas.rentadeherramientas.dto.response.AlquilerResponse;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.AlquilerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;

    @Autowired
    public AlquilerServiceImpl(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alquiler> findById(Long id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public Alquiler save(Alquiler alquiler) {
        return alquilerRepository.save(alquiler);
    }

    @Override
    public void deleteById(Long id) {
        alquilerRepository.deleteById(id);
    }

    @Override
    public List<Alquiler> obtenerPorEstado(String estado) {
        return alquilerRepository.findByEstado(estado);
    }

    @Override
    public List<Alquiler> obtenerTodos() {
        return alquilerRepository.findAll();
    }

    @Override
    public AlquilerResponse crearAlquiler(AlquilerRequest request) {
        // Validar que los datos requeridos no sean nulos
        if (request.getUser() == null || request.getTool() == null) {
            throw new IllegalArgumentException("Usuario y herramienta son requeridos");
        }

        // Validar fechas
        if (request.getFechaInicio().isAfter(request.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha fin");
        }

        // Crear el alquiler
        Alquiler alquiler = new Alquiler();
        alquiler.setUser(request.getUser());
        alquiler.setTool(request.getTool());
        alquiler.setFechaInicio(request.getFechaInicio());
        alquiler.setFechaFin(request.getFechaFin());
        alquiler.setCostoTotal(request.getCostoTotal());
        alquiler.setObservaciones(request.getObservaciones());
        alquiler.setEstado("PENDIENTE");
        
        // Guardar y retornar
        alquiler = alquilerRepository.save(alquiler);
        return mapToResponse(alquiler);
    }

    // Método auxiliar para mapear entidad a respuesta
    private AlquilerResponse mapToResponse(Alquiler alquiler) {
        return new AlquilerResponse(
            alquiler.getId(),
            alquiler.getUser(),
            alquiler.getTool(),
            alquiler.getFechaInicio(),
            alquiler.getFechaFin(),
            alquiler.getCostoTotal(),
            alquiler.getEstado(),
            alquiler.getObservaciones()
        );
    }
}