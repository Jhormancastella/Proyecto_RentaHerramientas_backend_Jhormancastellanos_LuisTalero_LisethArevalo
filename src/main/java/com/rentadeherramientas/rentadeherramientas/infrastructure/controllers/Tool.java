package com.rentadeherramientas.rentadeherramientas.infrastructure.controllers;

public interface Tool {

    Tool response = null;

    String getName();

    String getDescription();

    Object getPrice();

    Boolean isAvailable();

    void setId(Long id);

    void setName(String name);

    void setDescription(String description);

    void setAvailable(Object available);

    void setPrice(Object price);

}
