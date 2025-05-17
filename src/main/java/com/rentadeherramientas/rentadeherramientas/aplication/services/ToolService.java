package com.rentadeherramientas.rentadeherramientas.aplication.services;

import java.util.List;
import java.util.Optional;

import javax.tools.Tool;

import org.springframework.stereotype.Service;

@Service
public interface ToolService {
    List<Tool> findAll();

    Tool save(Tool tool);

    boolean existsByName(String name);

    List<Tool> getAllTools();

    Optional<Tool> getToolById(Long id);

    Object findById(Long id);
}
