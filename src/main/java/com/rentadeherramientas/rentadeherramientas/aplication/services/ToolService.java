package com.rentadeherramientas.rentadeherramientas.aplication.services;

import java.util.List;
import java.util.Optional;

import javax.tools.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.ToolRepository;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }

    public Optional<Tool> findById(Long id) {
        return toolRepository.findById(id);
    }

    public void delete(Long id) {
        toolRepository.deleteById(id);
    }

    public List<Tool> findByProveedor(Long proveedorId) {
        return toolRepository.findByProveedorId(proveedorId);
    }
}
