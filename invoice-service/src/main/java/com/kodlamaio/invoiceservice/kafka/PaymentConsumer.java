package com.kodlamaio.invoiceservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalInvoiceCreatedEvent;
import com.kodlamaio.common.events.rental.RentalPaymentCreatedEvent;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.request.CreateInvoiceRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentConsumer {

	private final InvoiceService invoiceService;
	private final ModelMapperService mapperService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
	
	@KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "RentalInvoiceCreate")
	public void consume(RentalInvoiceCreatedEvent event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
		CreateInvoiceRequest request =mapperService.forRequest().map(event, CreateInvoiceRequest.class);
		invoiceService.add(request);
	}
}
