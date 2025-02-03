package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.Enums.EventStatus;
import com.examle.eventlocator.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    //Find events by status
    List<Event> findByStatus(EventStatus status);

    //Find events by category
    List<Event> findByCategory(String category);

    //Find events that start after a specific date
    List<Event> findByStartDateAfter(LocalDateTime startDate);

    //Find events by a specific date range
    List<Event> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    //Find events by name
    List<Event> findByNameContaining(String name);

}
