package com.examle.eventlocator.service;

import com.examle.eventlocator.entity.Enums.EventStatus;
import com.examle.eventlocator.entity.Event;
import com.examle.eventlocator.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    //Create a new Event
    @Transactional
    public Event createEvent(String name, String description, String category, LocalDateTime startDate, LocalDateTime endDate, double ticketPrice, EventStatus status, String ticketInfo){

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setCategory(category);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setTicketPrice(ticketPrice);
        event.setTicketInfo(ticketInfo);
        event.setStatus(status);
        return eventRepository.save(event);


    }

    //Find event by ID
    public Optional<Event> findEventById(UUID eventId){
        return eventRepository.findById(eventId);
    }

    //Find event by status
    public List<Event> findEventsByStatus(EventStatus status){
        return eventRepository.findByStatus(status);
    }

    //Find events by category
    public List<Event> findEventsByCategory(String category){
        return eventRepository.findByCategory(category);
    }

    //Find events starting after a specific date
    public List<Event> findEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate){
        return eventRepository.findByStartDateBetween(startDate,endDate);
    }

    public List<Event> findUpcomingEvents(LocalDateTime startDate){
        return eventRepository.findByStartDateAfter(startDate);
    }

    //Search events by name
    public List<Event> searchEventsByName(String name){
        return eventRepository.findByNameContaining(name);
    }

    //Update event information
    @Transactional
    public Event updateEvent(UUID eventId, String name, String description, String category, LocalDateTime startDate, LocalDateTime endDate, double ticketPrice, String ticketInfo, EventStatus status){
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if(eventOptional.isEmpty()){
            throw new IllegalArgumentException("Event not found with ID: " + eventId);
        }
        Event event = eventOptional.get();
        event.setName(name);
        event.setDescription(description);
        event.setCategory(category);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setTicketPrice(ticketPrice);
        event.setTicketInfo(ticketInfo);
        event.setStatus(status);
        return eventRepository.save(event);
    }

    //Delete event by ID
    @Transactional
    public boolean deleteEvent(UUID eventId){
        if(eventRepository.existsById(eventId)){
            eventRepository.deleteById(eventId);
            return true;
        }
        return false;
    }


}
