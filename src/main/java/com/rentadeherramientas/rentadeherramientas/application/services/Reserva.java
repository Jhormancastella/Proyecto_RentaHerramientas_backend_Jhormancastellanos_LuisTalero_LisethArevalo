
package com.rentadeherramientas.rentadeherramientas.application.services;
import java.util.Date;
import java.util.List;

public interface Reserva {
    // Create a new reservation
    boolean crearReserva(Long clienteId, Long herramientaId, Date fechaInicio, Date fechaFin);
    
    // Cancel a reservation
    boolean cancelarReserva(Long reservaId);
    
    // Get reservation by ID
    Object obtenerReserva(Long reservaId);
    
    // Get all reservations
    List<?> obtenerTodasReservas();
    
    // Get reservations by client
    List<?> obtenerReservasPorCliente(Long clienteId);
    
    // Update reservation dates
    boolean actualizarFechasReserva(Long reservaId, Date nuevaFechaInicio, Date nuevaFechaFin);
    
    // Check if a tool is available for a specific period
    boolean verificarDisponibilidad(Long herramientaId, Date fechaInicio, Date fechaFin);
}
