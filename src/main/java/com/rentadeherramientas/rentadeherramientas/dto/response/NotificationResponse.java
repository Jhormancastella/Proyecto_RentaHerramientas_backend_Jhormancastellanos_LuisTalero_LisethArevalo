package com.rentadeherramientas.rentadeherramientas.dto.response;

import com.rentadeherramientas.rentadeherramientas.domain.entity.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class NotificationResponse {
    private Long id;
    private String titulo;
    private String mensaje;
    private NotificationType tipo;
    private String estado;
    private String enlace;
    private LocalDateTime fechaLectura;
    private LocalDateTime fechaCreacion;
}