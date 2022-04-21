package com.dinnerinmotion.eventsourcing.entity;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Event {
    @Id
    private UUID id;
    private String event;

    public Event(String event) {
        this.id = UUID.randomUUID();
        this.event = event;
    }

    public Event(UUID id, String event) {
        super();
        this.id = id;
        this.event = event;
    }
}
