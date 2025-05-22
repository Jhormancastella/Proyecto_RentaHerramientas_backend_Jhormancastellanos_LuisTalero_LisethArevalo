package com.rentadeherramientas.rentadeherramientas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Tool;
import com.rentadeherramientas.rentadeherramientas.domain.entity.User;

public class AlquilerResponse {

    public AlquilerResponse(Long id, User user, Tool tool, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            BigDecimal costoTotal, String estado, String observaciones) {
        //TODO Auto-generated constructor stub
    }

}
