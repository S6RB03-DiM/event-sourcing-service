package com.dinnerinmotion.eventsourcing;

import com.dinnerinmotion.eventsourcing.entity.Event;
import com.dinnerinmotion.eventsourcing.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;

import java.util.UUID;

@EnableMongoRepositories
@SpringBootApplication
@EnableEurekaClient
public class EventSourcingServiceApplication {
	private final Logger logger = LoggerFactory.getLogger(EventSourcingServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EventSourcingServiceApplication.class, args);
	}

	@RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2_000, maxDelay = 10_000, multiplier = 2))
	@KafkaListener(id = "testGroup", topics = "testTopic")
	public void listen(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
					   @Header(KafkaHeaders.OFFSET) long offset) {

		this.logger.info("Received: {} from {} @ {}", in, topic, offset);
		if (in.startsWith("fail")) {
			throw new RuntimeException("failed");
		}
	}
}
