package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.ToolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {
    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Transactional
    public Tool createTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Transactional(readOnly = true)
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tool> getToolById(Long id) {
        return toolRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Tool> getAvailableTools() {
        return toolRepository.findByAvailableTrue();
    }

    @Transactional
    public Tool updateTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Transactional
    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }

    public com.rentadeherramientas.rentadeherramientas.infrastructure.controllers.Tool save(
            com.rentadeherramientas.rentadeherramientas.infrastructure.controllers.Tool tool) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public boolean deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    public com.rentadeherramientas.rentadeherramientas.infrastructure.controllers.Tool findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List<com.rentadeherramientas.rentadeherramientas.infrastructure.controllers.Tool> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
