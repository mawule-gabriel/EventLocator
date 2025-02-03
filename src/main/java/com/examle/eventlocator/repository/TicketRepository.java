package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.Enums.TicketStatus;
import com.examle.eventlocator.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    //Find tickets by the status of the ticket
    List<Ticket> findByTicketStatus(TicketStatus ticketStatus);

    //Find tickets by the event
    List<Ticket> findByEvent_Id(UUID eventId);

    //Find tickets by event and ticket status
    List<Ticket> findByEvent_IdAndTicketStatus(UUID eventId, TicketStatus ticketStatus);

    //Find tickets by ticket type
    List<Ticket> findByTicketType(String ticketType);

}
