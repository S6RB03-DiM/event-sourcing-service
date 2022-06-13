package com.dinnerinmotion.eventsourcing;

import com.dinnerinmotion.eventsourcing.entity.Event;
import com.dinnerinmotion.eventsourcing.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.UUID;

@SpringBootTest
class EventSourcingServiceApplicationTests {

	@Autowired
	EventService eventService;

	@Test
	void contextLoads() {
	}

	@Test
	void eventIsSavedToRepository() {
		Event event = new Event(UUID.fromString("5e7e1f40-da91-11ec-9d64-0242ac120002"), "topic", "payload");
		event.setEventId(1L);

		eventService.saveEvent(event);
		assertEquals(eventService.findEventById(1L), event);
	}
}
