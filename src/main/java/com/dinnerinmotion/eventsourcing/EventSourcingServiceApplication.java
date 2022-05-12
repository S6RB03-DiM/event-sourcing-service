package com.dinnerinmotion.eventsourcing;

import com.dinnerinmotion.eventsourcing.controller.EventController;
import com.dinnerinmotion.eventsourcing.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class EventSourcingServiceApplication {
	private final String groupKafka = "eventSourcing";

	@Autowired
	private EventController eventController;

	public static void main(String[] args) {
		SpringApplication.run(EventSourcingServiceApplication.class, args);
	}

	@KafkaListener(id = groupKafka, topics = "mockTopic")
	public void processMessage(String message,
							   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List partitions,
							   @Header(KafkaHeaders.RECEIVED_TOPIC) List topics,
							   @Header(KafkaHeaders.OFFSET) List offsets) {

		System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);

		Event newEvent = new Event(UUID.randomUUID(), (String) topics.get(0), message);
		eventController.createEvent(newEvent);
	}
}
