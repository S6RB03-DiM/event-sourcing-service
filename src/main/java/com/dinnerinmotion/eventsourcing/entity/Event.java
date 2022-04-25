package com.dinnerinmotion.eventsourcing.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;
    private UUID entityId;
    private String topic;
    private String payload;

    public Event(UUID entityId, String topic, String payload) {
        this.entityId = entityId;
        this.topic = topic;
        this.payload = payload;
    }
}
