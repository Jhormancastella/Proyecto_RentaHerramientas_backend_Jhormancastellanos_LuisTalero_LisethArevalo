package com.rentadeherramientas.rentadeherramientas.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Producto;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.ProductoRepository;

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

    public Object obtenerPorCategoria(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorCategoria'");
    }

    public Object obtenerTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodos'");
    }

    // Puedes agregar más métodos según sea necesario
}
