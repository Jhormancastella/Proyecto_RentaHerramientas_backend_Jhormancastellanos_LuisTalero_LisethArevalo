package com.rentadeherramientas.rentadeherramientas.application.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Reservation;
import com.rentadeherramientas.rentadeherramientas.infraestructure.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;




@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStartDate(reservationDetails.getStartDate());
            reservation.setEndDate(reservationDetails.getEndDate());
            reservation.setStatus(reservationDetails.getStatus());
            reservation.setTool(reservationDetails.getTool());
            reservation.setUser(reservationDetails.getUser());
            return reservationRepository.save(reservation);
        });
    }

    public boolean deleteReservation(Long id) {
        return reservationRepository.findById(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
    }
}