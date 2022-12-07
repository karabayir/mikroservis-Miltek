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
import com.kodlamaio.common.events.brand.BrandDeletedEvent;
import com.kodlamaio.common.events.brand.BrandUpdatedEvent;
import com.kodlamaio.common.events.car.CarDeletedEvent;
import com.kodlamaio.common.events.car.CarRentalDeletedEvent;
import com.kodlamaio.common.events.car.CarUpdatedEvent;
import com.kodlamaio.common.events.model.ModelDeletedEvent;
import com.kodlamaio.common.events.model.ModelUpdatedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.common.events.rental.RentalUpdatedCarEvent;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class InventoryProducer {

	 private static final Logger LOGGER = LoggerFactory.getLogger(InventoryProducer.class); 
	 private final NewTopic topic;
	 
	 private final KafkaTemplate<String, InventoryCreatedEvent> kafkaTemplateCreate;
	 private final KafkaTemplate<String, CarUpdatedEvent> kafkaTemplateCarUpdate;
	 private final KafkaTemplate<String, CarDeletedEvent> kafkaTemplateCarDelete;
	 private final KafkaTemplate<String, BrandUpdatedEvent> kafkaTemplateBrandUpdate;
	 private final KafkaTemplate<String, BrandDeletedEvent> kafkaTemplateBrandDelete;
	 private final KafkaTemplate<String, ModelUpdatedEvent> kafkaTemplateModelUpdate;
	 private final KafkaTemplate<String, ModelDeletedEvent> kafkaTemplateModelDelete;
	 private final KafkaTemplate<String, RentalCreatedEvent> kafkaTemplateRentalCreate;
	 private final KafkaTemplate<String, RentalUpdatedCarEvent> kafkaTemplateRentalUpdate;
	 private final KafkaTemplate<String, CarRentalDeletedEvent> kafkaTemplateRentalDelete;
	  
	

	public void sendMessage(InventoryCreatedEvent event) {
	        LOGGER.info(String.format("Inventory created event => %s", event.toString()));

	        Message<InventoryCreatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateCreate.send(message);
	    }
	 
	 public void sendMessage(CarUpdatedEvent event) {
	        LOGGER.info(String.format("Car updated event => %s", event.toString()));

	        Message<CarUpdatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateCarUpdate.send(message);
	    }
	 
	 public void sendMessage(CarDeletedEvent event) {
	        LOGGER.info(String.format("Car deleted event => %s", event.toString()));

	        Message<CarDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateCarDelete.send(message);
	    }
	 
	 public void sendMessage(BrandUpdatedEvent event) {
	        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

	        Message<BrandUpdatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateBrandUpdate.send(message);
	    }
	 
	    public void sendMessage(BrandDeletedEvent event) {
	        LOGGER.info(String.format("Brand deleted event => %s", event.toString()));

	        Message<BrandDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateBrandDelete.send(message);
	    }
	    
	    public void sendMessage(ModelUpdatedEvent event) {
	        LOGGER.info(String.format("Model updated event => %s", event.toString()));

	        Message<ModelUpdatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateModelUpdate.send(message);
	    }
	    
	    public void sendMessage(ModelDeletedEvent event) {
	        LOGGER.info(String.format("Model deleted event => %s", event.toString()));

	        Message<ModelDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateModelDelete.send(message);
	    }
	    
	    public void sendMessage(RentalCreatedEvent event) {
	        LOGGER.info(String.format("Car Rental created event => %s", event.toString()));

	        Message<RentalCreatedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateRentalCreate.send(message);
	    }
	    
	    public void sendMessage(RentalUpdatedCarEvent event) {
	        LOGGER.info(String.format("Car rental updated event => %s", event.toString()));
	        
	        Message<RentalUpdatedCarEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateRentalUpdate.send(message);
	    }
	    
	    public void sendMessage(CarRentalDeletedEvent event) {
	        LOGGER.info(String.format("Car rental deleted event => %s", event.toString()));

	        Message<CarRentalDeletedEvent> message = MessageBuilder
	                .withPayload(event)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        kafkaTemplateRentalDelete.send(message);
	    }
	    
}
