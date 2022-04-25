package com.dinnerinmotion.eventsourcing.service;
import com.dinnerinmotion.eventsourcing.entity.Event;
import com.dinnerinmotion.eventsourcing.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        log.info("inside save event method of EventService");
        return eventRepository.save(event);
    }

    public Event findEventById(Long eventId) {
        log.info("inside find event by id method of EventService");
        return eventRepository.findByEventId(eventId);
    }
}
