package com.rentadeherramientas.rentadeherramientas.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentadeherramientas.rentadeherramientas.application.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<Object> obtenerTodosProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    @GetMapping("/categoria/{id}")
    public ResponseEntity<Object> obtenerProductosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(id));
    }
}
