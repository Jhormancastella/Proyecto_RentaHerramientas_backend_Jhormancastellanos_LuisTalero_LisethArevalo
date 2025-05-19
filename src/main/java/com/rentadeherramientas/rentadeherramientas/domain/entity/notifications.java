package com.rentadeherramientas.rentadeherramientas.domain.entity;

public class notifications {
    private Long id;
    private String message;
    private String recipient;
    private boolean read;

    public notifications() {
    }

    public notifications(Long id, String message, String recipient, boolean read) {
        this.id = id;
        this.message = message;
        this.recipient = recipient;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
