package com.kodlamaio.rentalservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalUpdatedCarEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalUpdateProducer {

	  private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateProducer.class);

	    private final NewTopic topic;
	    private final KafkaTemplate<String, RentalUpdatedCarEvent> kafkaTemplate;
	    
	    
	    public void sendMessage(RentalUpdatedCarEvent rentalUpdatedCarEvent){
	    	LOGGER.info(String.format("Rental updated car event => %s", rentalUpdatedCarEvent.toString()));
	    	Message<RentalUpdatedCarEvent> message= MessageBuilder
	    			.withPayload(rentalUpdatedCarEvent)
	    			.setHeader(KafkaHeaders.TOPIC, "rental-updated")
	    			.build();
	    	
	    	kafkaTemplate.send(message);
	    }
}
