package com.kodlamaio.rentalservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.common.events.RentalCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCreateProducer {

	  private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateProducer.class);

	    private final NewTopic topic;
	    private final KafkaTemplate<String, RentalCreatedEvent> kafkaTemplate;
	    private final KafkaTemplate<String, PaymentCreatedEvent> kafkaTemplatePaymentCreated;
	    
	    public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
	        LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));

	        Message<RentalCreatedEvent> message = MessageBuilder
	                .withPayload(rentalCreatedEvent)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        
	        kafkaTemplate.send(message);
	    }
	    
	    public void sendMessage(PaymentCreatedEvent paymentCreatedEvent) {
	        LOGGER.info(String.format("Rental created event => %s", paymentCreatedEvent.toString()));

	        Message<PaymentCreatedEvent> message = MessageBuilder
	                .withPayload(paymentCreatedEvent)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        
	        kafkaTemplatePaymentCreated.send(message);
	    }
	    
	    
 }
 


