package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

import java.util.List;

public interface PagoService {

    <Pago> List<Pago> getAllPagos();

    <Pago> Pago getPagoById(Long id);

    <Pago> Pago createPago(Pago pago);

    <Pago> Pago updatePago(Long id, Pago pago);

    boolean deletePago(Long id);

}
