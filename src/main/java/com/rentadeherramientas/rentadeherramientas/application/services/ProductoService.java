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

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        Optional<Producto> existente = productoRepository.findById(id);
        if (existente.isPresent()) {
            Producto prod = existente.get();
            prod.setNombre(producto.getNombre());
            prod.setDescripcion(producto.getDescripcion());
            prod.setPrecio(producto.getPrecio());
            prod.setStock(producto.getStock());
            prod.setCategoria(producto.getCategoria());
            prod.setDisponible(producto.isDisponible());
            prod.setImagenUrl(producto.getImagenUrl());
            // Agrega aquí otros campos si es necesario
            return productoRepository.save(prod);
        } else {
            return null;
        }
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Puedes agregar más métodos según sea necesario
}
