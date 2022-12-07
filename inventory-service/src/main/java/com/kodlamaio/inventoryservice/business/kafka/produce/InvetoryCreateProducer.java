package com.kodlamaio.inventoryservice.business.kafka.produce;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.InventoryCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvetoryCreateProducer {

	 private static final Logger LOGGER = LoggerFactory.getLogger(InvetoryCreateProducer.class); 
	 private final NewTopic topic;
	 private final KafkaTemplate<String, InventoryCreatedEvent> kafkaTemplate;
	 
	 public void sendMessage( InventoryCreatedEvent event) {
	        LOGGER.info(String.format("Inventory created event => %s", event.toString()));

	        Message<InventoryCreatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplate.send(message);
	    }
}
