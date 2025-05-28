package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Producto;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Reservation;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.ProductoRepository;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.ReservationRepository;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                            UserRepository userRepository,
                            ProductoRepository productoRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.productoRepository = productoRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        // Validar que la reserva tenga usuario y producto
        if (reservation.getUser() == null || reservation.getUser().getId() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio para crear una reserva");
        }

        if (reservation.getProducto() == null || reservation.getProducto().getId() == null) {
            throw new IllegalArgumentException("El producto es obligatorio para crear una reserva");
        }

        // Verificar que existan el usuario y el producto
        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + reservation.getUser().getId()));

        Producto producto = productoRepository.findById(reservation.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto not found with ID: " + reservation.getProducto().getId()));

        // Establecer las relaciones
        reservation.setUser(user);
        reservation.setProducto(producto);

        // Validar fechas
        if (reservation.getStartDate() == null || reservation.getEndDate() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias");
        }

        if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        return reservationRepository.save(reservation);
    }

    @Transactional
    public Optional<Reservation> updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(reservation -> {
            // Actualizar solo campos permitidos
            reservation.setStartDate(reservationDetails.getStartDate());
            reservation.setEndDate(reservationDetails.getEndDate());
            reservation.setStatus(reservationDetails.getStatus());
            reservation.setNotes(reservationDetails.getNotes());
            
            // Validar fechas
            if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
            }
            
            return reservationRepository.save(reservation);
        });
    }

    @Transactional
    public boolean deleteReservation(Long id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
    }
}