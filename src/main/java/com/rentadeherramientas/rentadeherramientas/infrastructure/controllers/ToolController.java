package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentadeherramientas.rentadeherramientas.application.services.ToolService;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        Tool tool = toolService.findById(id);
        if (tool != null) {
            return ResponseEntity.ok(tool);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Tool createTool(@RequestBody Tool tool) {
        return toolService.save(tool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        if (toolService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
