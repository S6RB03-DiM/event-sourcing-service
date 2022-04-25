package com.dinnerinmotion.eventsourcing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dinnerinmotion.eventsourcing.entity.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventId(Long id);
}
