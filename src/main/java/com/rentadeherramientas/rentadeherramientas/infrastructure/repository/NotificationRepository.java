package com.rentadeherramientas.rentadeherramientas.infrastructure.repository;

import com.rentadeherramientas.rentadeherramientas.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdAndReadAtIsNotNull(Long userId);
    List<Notification> findByUserIdAndReadAtIsNull(Long userId);
}