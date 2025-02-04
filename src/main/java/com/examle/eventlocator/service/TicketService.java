package com.examle.eventlocator.service;

import com.examle.eventlocator.entity.Enums.TicketStatus;
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

    //Sell a Ticket
    @Transactional
    public Ticket sellTicket(UUID ticketId, int quantity){
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if(ticketOptional.isEmpty()){
            throw new IllegalArgumentException("Ticket not found with ID: " + ticketId);
      }
        Ticket ticket = ticketOptional.get();
        if(ticket.getAvailable_quantity() < quantity){
            throw new IllegalArgumentException("Not enough tickets available");
        }

        ticket.setAvailable_quantity(ticket.getAvailable_quantity() - quantity);
        ticket.setSoldQuantity(ticket.getSoldQuantity() + quantity);

        if (ticket.getAvailable_quantity() == 0){
            ticket.setTicketStatus(TicketStatus.CONFIRMED);
        }

        return ticketRepository.save(ticket);
    }





}
