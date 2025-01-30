package com.examle.eventlocator.entity;

import com.examle.eventlocator.entity.Enums.TicketStatus;
import com.examle.eventlocator.entity.Enums.TicketType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketType ticketType;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double available_quantity;

    @Column(nullable = false)
    private int soldQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private Ticket(TicketType ticketType, double price, int available_quantity, Event event){
        this.ticketType = ticketType;
        this.price = price;
        this.available_quantity = available_quantity;
        this.event = event;
        this.soldQuantity = 0;
        this.ticketStatus = TicketStatus.PENDING;
    }





}

