package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    // Ejemplo de lista en memoria (debes reemplazar por servicio real)
    private List<String> invoices = new ArrayList<>();

    @GetMapping
    public List<String> getAllInvoices() {
        return invoices;
    }

    @PostMapping
    public String createInvoice(@RequestBody String invoice) {
        invoices.add(invoice);
        return "Invoice created";
    }
}
