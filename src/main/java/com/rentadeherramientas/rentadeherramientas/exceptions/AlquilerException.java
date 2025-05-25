package com.rentadeherramientas.rentadeherramientas.exceptions;


public class AlquilerException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public AlquilerException(String message) {
        super(message);
    }

    public AlquilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlquilerException(Throwable cause) {
        super(cause);
    }

    public AlquilerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}