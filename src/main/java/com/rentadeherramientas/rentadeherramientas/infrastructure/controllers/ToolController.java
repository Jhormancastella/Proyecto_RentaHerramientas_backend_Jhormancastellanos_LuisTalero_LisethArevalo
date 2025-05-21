package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentadeherramientas.rentadeherramientas.application.services.ToolService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(origins = "*")
public class ToolController {
    
    @Autowired
    private ToolService toolService;

    @GetMapping
    public ResponseEntity<List<Tool>> getAllTools() {
        return ResponseEntity.ok(toolService.getAllTools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return toolService.getToolById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createTool(@RequestBody Tool tool) {
        return ResponseEntity.ok(toolService.saveTool(tool));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        return ResponseEntity.ok(toolService.updateTool(id, tool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }
}
