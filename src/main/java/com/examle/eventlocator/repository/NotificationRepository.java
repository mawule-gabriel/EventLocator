package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.Enums.NotificationStatus;
import com.examle.eventlocator.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    //Find notification by status
    List<Notification> findByStatus(NotificationStatus status);

    //Find notifications by user ID
    List<Notification> findByUser_Id(UUID user_id);

    //Find notifications by event ID
    List<Notification> findByEvent_Id(UUID eventId);

    //Find notifications by event and status
    List<Notification> findByEvent_IdAndStatus(UUID eventId, NotificationStatus status);

}
