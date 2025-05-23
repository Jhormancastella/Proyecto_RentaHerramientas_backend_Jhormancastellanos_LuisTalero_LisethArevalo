package com.rentadeherramientas.rentadeherramientas.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Producto;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    public List<Producto> obtenerPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Puedes agregar más métodos según sea necesario
}
