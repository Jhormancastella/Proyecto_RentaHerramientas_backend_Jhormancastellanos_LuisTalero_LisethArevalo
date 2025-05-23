package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import com.rentadeherramientas.rentadeherramientas.domain.entity.*;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.*;

import jakarta.transaction.Transactional;

@Configuration
public class DataSeeder implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private ProveedorRepository proveedorRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Roles
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
            .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_ADMIN)));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_USER)));
        Role proveedorRole = roleRepository.findByName(RoleName.ROLE_PROVIDER)
            .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_PROVIDER)));

        // Usuarios normales
        for (int i = 1; i <= 5; i++) {
            String username = "user" + i;
            if (!userRepository.existsByUsername(username)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode("password" + i));
                user.setEmail(username + "@email.com");
                user.setFirstName("Nombre" + i);
                user.setLastName("Apellido" + i);
                user.setActive(true);
                user.getRoles().add(userRole);
                userRepository.save(user);
            }
        }

        // Proveedores (usuarios con rol proveedor)
        for (int i = 1; i <= 5; i++) {
            String username = "proveedor" + i;
            if (!userRepository.existsByUsername(username)) {
                User proveedor = new User();
                proveedor.setUsername(username);
                proveedor.setPassword(passwordEncoder.encode("proveedor123"));
                proveedor.setEmail("prov" + i + "@email.com");
                proveedor.setFirstName("Proveedor" + i);
                proveedor.setLastName("Empresa");
                proveedor.setActive(true);
                proveedor.getRoles().add(proveedorRole);
                userRepository.save(proveedor);
            }
        }

        // Proveedores (entidad)
        for (int i = 1; i <= 5; i++) {
            String nombre = "Proveedor" + i;
            if (!proveedorRepository.existsByNombre(nombre)) {
                Proveedor prov = new Proveedor();
                prov.setNombre(nombre);
                prov.setEmail("prov" + i + "@email.com");
                prov.setActivo(true);
                prov.setContacto("Contacto" + i);
                prov.setCiudad("Ciudad" + i);
                prov.setTelefono("12345678" + i);
                proveedorRepository.save(prov);
            }
        }

        // Productos
        String[] nombres = {"Martillo", "Taladro", "Sierra", "Destornillador", "Alicate"};
        String[] descripciones = {
            "Martillo de acero", "Taladro eléctrico", "Sierra manual", "Destornillador de estrella", "Alicate universal"
        };
        for (int i = 0; i < 5; i++) {
            String nombre = nombres[i];
            if (!productoRepository.existsByNombre(nombre)) {
                Producto prod = new Producto();
                prod.setNombre(nombre);
                prod.setDescripcion(descripciones[i]);
                prod.setPrecio(10000.0 + i * 5000);
                prod.setStock(10 + i * 2);
                prod.setCategoria("Herramientas");
                prod.setDisponible(true);
                productoRepository.save(prod);
            }
        }
    }
}