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

    @Transactional
    public Tool saveTool(Tool tool) {
        return toolRepository.save(tool);
    }

    @Transactional
    public Tool updateTool(Long id, Tool tool) {
        Optional<Tool> existingTool = toolRepository.findById(id);
        if (existingTool.isPresent()) {
            tool.setId(id);
            return toolRepository.save(tool);
        }
        throw new RuntimeException("Tool not found with id: " + id);
    }
}