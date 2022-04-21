package com.dinnerinmotion.eventsourcing.controller;

import com.dinnerinmotion.eventsourcing.entity.Event;
import com.dinnerinmotion.eventsourcing.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@Slf4j
public class EventController {
    private final KafkaTemplate<Object, Object> template;
    private final EventRepository eventRepository;

    @Autowired
    public EventController(KafkaTemplate<Object, Object> template, EventRepository eventRepository){
        this.template = template;
        this.eventRepository = eventRepository;
    }

    @GetMapping(path = "/test/{test}")
    public void sendFoo(@PathVariable String test) {
        this.template.send("testTopic", test);
    }

    @GetMapping("/{eventId}")
    public Event findEventById(@PathVariable("eventId") UUID eventId){
        log.info("inside find event by id method of EventController");
        return eventRepository.findEventById(eventId);
    }

    @PostMapping("/seed")
    public boolean seedEvents(){
        eventRepository.save(new Event("eventTest1"));
        eventRepository.save(new Event("eventTest2"));
        eventRepository.save(new Event("eventTest3"));
        eventRepository.save(new Event("eventTest4"));
        return true;
    }

    @PostMapping("/")
    public Event createEvent(@RequestBody Event event){
        log.info("inside create event method of EventController");
        return eventRepository.save(event);
    }
}
