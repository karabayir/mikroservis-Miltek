package com.kodlamaio.paymentservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.PaymentInvoiceCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentCreateProducer {

	 private static final Logger LOGGER = LoggerFactory.getLogger(PaymentCreateProducer.class);
	 
	 private final NewTopic topic;
	 private final KafkaTemplate<String, PaymentInvoiceCreatedEvent> kafkaTemplate;
	 
	 public void sendMessage(PaymentInvoiceCreatedEvent paymentInvoiceCreatedEvent) {
	        LOGGER.info(String.format("Rental created event => %s", paymentInvoiceCreatedEvent.toString()));

	        Message<PaymentInvoiceCreatedEvent> message = MessageBuilder
	                .withPayload(paymentInvoiceCreatedEvent)
	                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
	        
	        kafkaTemplate.send(message);
	    }
}
