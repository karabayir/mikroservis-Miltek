package com.kodlamaio.paymentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalPaymentCreatedEvent;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.request.CreatePaymentRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {

	private final PaymentService paymentService;
	private final ModelMapperService mapperService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	
	@KafkaListener(topics = "payment-created",groupId = "RentalPaymentCreate")
	public void consume(RentalPaymentCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		CreatePaymentRequest request =mapperService.forRequest().map(event, CreatePaymentRequest.class);
		request.setRentalId(event.getRentalId());
		paymentService.add(request);
	}
}
