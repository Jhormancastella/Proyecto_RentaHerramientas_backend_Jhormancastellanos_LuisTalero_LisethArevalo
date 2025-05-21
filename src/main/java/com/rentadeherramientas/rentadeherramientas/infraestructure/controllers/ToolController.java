package com.rentadeherramientas.rentadeherramientas.infraestructure.controllers;

import com.rentadeherramientas.rentadeherramientas.application.services.ToolService;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        Optional<Tool> tool = toolService.getToolById(id);
        return tool.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tool createTool(@RequestBody Tool tool) {
        return toolService.createTool(tool);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        if (!toolService.getToolById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tool.setId(id);
        Tool updatedTool = toolService.updateTool(tool);
        return ResponseEntity.ok(updatedTool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        if (!toolService.getToolById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public List<Tool> getAvailableTools() {
        return toolService.getAvailableTools();
    }
}
