package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Reservation;
import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.ReservationRepository;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.ToolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ToolRepository toolRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ToolRepository toolRepository) {
        this.reservationRepository = reservationRepository;
        this.toolRepository = toolRepository;
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Tool tool = reservation.getTool();
        tool.setAvailable(false);
        toolRepository.save(tool);
        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Transactional
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Tool tool = reservation.get().getTool();
            tool.setAvailable(true);
            toolRepository.save(tool);
            reservationRepository.deleteById(id);
        }
    }
}