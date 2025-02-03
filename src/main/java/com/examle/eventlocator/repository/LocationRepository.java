package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    //Find locations by event ID
    List<Location> findByEvent_Id(UUID eventId);

    //Find locations by name
    List<Location> findByName(String name);

    //Find locations by address
    List<Location> findByAddressContaining(String address);

}
