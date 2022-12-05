package com.kodlamaio.rentalservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalInvoiceCreatedEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalInvoiceCreateProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateProducer.class);

    private final NewTopic topic;
    private final KafkaTemplate<String, RentalInvoiceCreatedEvent> kafkaTemplate;
    
    public void sendMessage(RentalInvoiceCreatedEvent invoiceCreatedEvent) {
        LOGGER.info(String.format("Rental created event => %s", invoiceCreatedEvent.toString()));

        Message<RentalInvoiceCreatedEvent> message = MessageBuilder
                .withPayload(invoiceCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        
        kafkaTemplate.send(message);
    }
}
