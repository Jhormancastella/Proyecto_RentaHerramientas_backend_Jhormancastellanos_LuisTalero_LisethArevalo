package com.rentadeherramientas.rentadeherramientas.aplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Proveedor;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Optional<Proveedor> obtenerPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
}

