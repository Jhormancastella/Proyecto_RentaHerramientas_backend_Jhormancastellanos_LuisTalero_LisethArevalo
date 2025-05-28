package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

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
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private ReservationRepository reservationRepository;

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


        String adminUsername = "admin";
        if (!userRepository.existsByUsername(adminUsername)) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@email.com");
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setActive(true);
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
        }

        // Usuarios normales (se mantiene igual)
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

        // Proveedores (usuarios con rol proveedor) (se mantiene igual)
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

        // Proveedores (entidad) (se mantiene igual)
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

        // Productos (se mantiene igual)
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

        User user = userRepository.findByUsername("user1").orElse(null);
        List<Producto> productos = productoRepository.findAll();

        if (user != null && !productos.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                Reservation reservation = new Reservation();
                reservation.setUser(user);
                reservation.setProducto(productos.get(i % productos.size()));
                reservation.setStartDate(LocalDateTime.now().plusDays(i * 2));
                reservation.setEndDate(LocalDateTime.now().plusDays(i * 2 + 3));
                reservation.setStatus(ReservationStatus.CONFIRMED);
                reservation.setTotalAmount(BigDecimal.valueOf(5000.0 * i));
                reservation.setNotes("Reserva de ejemplo " + i);
                reservationRepository.save(reservation);
            }
        }


        // Pagos (completamente corregido con todos los campos requeridos)
        for (int i = 1; i <= 5; i++) {
            Payment payment = new Payment();
            
            // Campos básicos
            payment.setAmount(5000.0 * i);
            payment.setPaymentDate(LocalDateTime.now().minusDays(i));
            
            // Campos de estado (status)
            PaymentStatus status;
            switch (i % 5) {
                case 0: status = PaymentStatus.PENDING; break;
                case 1: status = PaymentStatus.PROCESSING; break;
                case 2: status = PaymentStatus.COMPLETED; break;
                case 3: status = PaymentStatus.FAILED; break;
                default: status = PaymentStatus.REFUNDED;
            }
            payment.setStatus(status);
            
            // Campos de método de pago (method)
            PaymentMethod method;
            switch (i % 5) {
                case 0: method = PaymentMethod.CREDIT_CARD; break;
                case 1: method = PaymentMethod.DEBIT_CARD; break;
                case 2: method = PaymentMethod.CASH; break;
                case 3: method = PaymentMethod.BANK_TRANSFER; break;
                default: method = PaymentMethod.DIGITAL_WALLET;
            }
            payment.setMethod(method);
            
            // Campos adicionales requeridos
            payment.setCreatedAt(LocalDateTime.now().minusDays(i));
            payment.setUpdatedAt(LocalDateTime.now().minusDays(i));
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentDetails("Detalles del pago #" + i);
            
            // Relación con reserva
            if (reservationRepository.count() > 0) {
                List<Reservation> reservations = reservationRepository.findAll();
                if (!reservations.isEmpty()) {
                    payment.setReservation(reservations.get(i % reservations.size()));
                }
            }
            
            paymentRepository.save(payment);
        }
    }
}