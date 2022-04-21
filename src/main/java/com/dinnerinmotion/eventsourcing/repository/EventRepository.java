package com.dinnerinmotion.eventsourcing.repository;

import com.dinnerinmotion.eventsourcing.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends MongoRepository<Event, UUID> {

    public Event findEventById(UUID id);
    public List<Event> findByEvent(String event);

}
