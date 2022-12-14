package com.kodlamaio.inventoryservice.kafka.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.InventoryCreatedEvent;
import com.kodlamaio.common.events.brand.BrandDeletedEvent;
import com.kodlamaio.common.events.brand.BrandUpdatedEvent;
import com.kodlamaio.common.events.car.CarDeletedEvent;
import com.kodlamaio.common.events.car.CarRentalDeletedEvent;
import com.kodlamaio.common.events.car.CarUpdatedEvent;
import com.kodlamaio.common.events.model.ModelDeletedEvent;
import com.kodlamaio.common.events.model.ModelUpdatedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryProducer.class);
	
	private final NewTopic topic;
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(InventoryCreatedEvent event) {
		LOGGER.info(String.format("Inventory Created Event => %s", event.toString()));
		
		Message<InventoryCreatedEvent> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC,"inventory-car-created").build();
		kafkaTemplate.send(message);
	}
	
	public void sendMessage(CarUpdatedEvent event) {
		LOGGER.info(String.format("Inventory Created Event => %s", event.toString()));
		
		Message<CarUpdatedEvent> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, "inventory-car-updated").build();
		kafkaTemplate.send(message);
	}
	
	public void sendMessage(CarDeletedEvent event) {
		LOGGER.info(String.format("Inventory Created Event => %s", event.toString()));
		
		Message<CarDeletedEvent> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, "inventory-car-deleted").build();
		kafkaTemplate.send(message);
	}
	
	 public void sendMessage(BrandUpdatedEvent event) {
	        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

	        Message<BrandUpdatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, "inventory-brand-updated").build();

	        kafkaTemplate.send(message);
	    }
	 
	 
	   public void sendMessage(BrandDeletedEvent event) {
	        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

	        Message<BrandDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, "inventory-brand-deleted").build();

	        kafkaTemplate.send(message);
	    }
	   
	    public void sendMessage(ModelUpdatedEvent event) {
	        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

	        Message<ModelUpdatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, "inventory-model-updated").build();

	        kafkaTemplate.send(message);
	    }
	    
	     public void sendMessage(ModelDeletedEvent event) {
	        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

	        Message<ModelDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC,"inventory-model-deleted").build();

	        kafkaTemplate.send(message);
	    }
	     
	     public void sendMessage(RentalCreatedEvent event) {
		        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

		        Message<RentalCreatedEvent> message = MessageBuilder
		                .withPayload(event)
		                .setHeader(KafkaHeaders.TOPIC, "inventory-rental-created").build();

		        kafkaTemplate.send(message);
		}
	     public void sendMessage(CarRentalDeletedEvent event) {
		        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

		        Message<CarRentalDeletedEvent> message = MessageBuilder
		                .withPayload(event)
		                .setHeader(KafkaHeaders.TOPIC, "inventory-rental-deleted").build();

		        kafkaTemplate.send(message);
		    }
	
}
