package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.Booking;
import com.examle.eventlocator.entity.Enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    //Find bookings by user ID
    List<Booking> findByUser_Id(UUID userId);

    //Find bookings by ticket ID
    List<Booking> findByTicket_Id(UUID userId);

    //Find bookings by status
    List<Booking> findByStatus(TicketStatus status);

    //Find bookings by user and ticket status
    List<Booking> findByUser_IdAndStatus(UUID userId, TicketStatus status);

    //Find bookings by quantity greater than a specific value
    List<Booking> findByQuantityGreaterThan(int quantity);

}
