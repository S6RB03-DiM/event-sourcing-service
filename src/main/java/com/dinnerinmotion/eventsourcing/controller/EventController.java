package com.dinnerinmotion.eventsourcing.controller;

import com.dinnerinmotion.eventsourcing.entity.Event;
import com.dinnerinmotion.eventsourcing.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@Slf4j
public class EventController {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Autowired
    private EventService eventService;

    @GetMapping("/{eventId}")
    public Event findEventById(@PathVariable("eventId") Long eventId) {
        log.info("inside find event by id method of EventController");
        return eventService.findEventById(eventId);
    }

    @GetMapping("/send/{id}")
    public void testo(@PathVariable("id") Long eventId) {
        log.info("Send event mq");
        Event sendEvent = eventService.findEventById(eventId);
        this.template.send("events", sendEvent);
    }

    public void createEvent(Event event) {
        log.info("inside create event method of EventController");
        eventService.saveEvent(event);
    }

    @GetMapping(value="test")
    public String getAccountsByEmpId() {
        return "hello world";
    }
}
