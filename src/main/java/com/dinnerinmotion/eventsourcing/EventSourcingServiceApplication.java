package com.dinnerinmotion.eventsourcing;

import com.dinnerinmotion.eventsourcing.controller.EventController;
import com.dinnerinmotion.eventsourcing.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class EventSourcingServiceApplication {
	private final Logger logger = LoggerFactory.getLogger(EventSourcingServiceApplication.class);

	@Autowired
	private EventController eventController;

	public static void main(String[] args) {
		SpringApplication.run(EventSourcingServiceApplication.class, args);
	}

	@RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2_000, maxDelay = 10_000, multiplier = 2))
	@KafkaListener(id = "eventGroup", topics = "mockTopic")
	public void listen(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
					   @Header(KafkaHeaders.OFFSET) long offset) {

		this.logger.info("Received: {} from {} @ {}", in, topic, offset);

		if (in.startsWith("fail")) {
			throw new RuntimeException("failed");
		}

		Event newEvent = new Event(UUID.randomUUID(), topic, in);
		eventController.createEvent(newEvent);
	}
}
