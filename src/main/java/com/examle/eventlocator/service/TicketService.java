package com.examle.eventlocator.service;

import com.examle.eventlocator.entity.Enums.TicketType;
import com.examle.eventlocator.entity.Event;
import com.examle.eventlocator.entity.Ticket;
import com.examle.eventlocator.repository.EventRepository;
import com.examle.eventlocator.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository){
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    //Create a new Ticket
    @Transactional
    public Ticket createTicket(TicketType ticketType, double price, int availableQuantity, UUID eventId){
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()){
            throw new IllegalArgumentException("Event not found with ID: " + eventId);
        }
        Event event = eventOptional.get();
        Ticket ticket = new Ticket(ticketType, price, availableQuantity, event);
        return ticketRepository.save(ticket);
    }




}
