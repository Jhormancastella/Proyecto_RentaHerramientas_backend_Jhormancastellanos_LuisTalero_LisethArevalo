package com.rentadeherramientas.rentadeherramientas.application.services;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Notification;
import com.rentadeherramientas.rentadeherramientas.infrastructure.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Transactional
    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdAndReadAtIsNull(userId);
    }

    @Transactional
    public Notification markAsRead(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            Notification n = notification.get();
            n.setReadAt(LocalDateTime.now());
            return notificationRepository.save(n);
        }
        return null;
    }

    @Transactional
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
