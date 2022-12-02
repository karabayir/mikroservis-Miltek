package com.kodlamaio.rentalservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedCarEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalProducer {

	  private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);

	    private final NewTopic topic;
	    private final KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate;
	    private final KafkaTemplate<String, RentalUpdatedCarEvent> kafkaTemplate2;
	    
	    public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
	        LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));

	        Message<RentalCreatedEvent> message = MessageBuilder
	                .withPayload(rentalCreatedEvent)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        
	        kafkaTemplate.send(message);
	    }
	    
	    public void sendMessage(RentalUpdatedCarEvent rentalUpdatedCarEvent){
	    	LOGGER.info(String.format("Rental updated car event => %s", rentalUpdatedCarEvent.toString()));
	    	Message<RentalUpdatedCarEvent> message= MessageBuilder
	    			.withPayload(rentalUpdatedCarEvent)
	    			.setHeader(KafkaHeaders.TOPIC, topic.name())
	    			.build();
	    	
	    	kafkaTemplate2.send(message);
	    }
 }


