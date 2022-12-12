package com.kodlamaio.inventoryservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCreateConsumer {

	   private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateConsumer.class);
	   
	   CarService carService;

	    @KafkaListener(
	            topics = "rental-created"
	            ,groupId = "rental-create"
	    )   
	    
	    public void consume(@Payload RentalCreatedEvent event){
	        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

	        carService.changeCarState(event.getCarId());
	        LOGGER.info(event.getCarId()+" state changed");
	    }
	    
	   
}
