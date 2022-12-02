package com.kodlamaio.inventoryservice.business.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedCarEvent;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalConsumer {

	   private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
	   
	   CarService carService;

	    @KafkaListener(
	            topics = "${spring.kafka.topic.name}"
	            ,groupId = "${spring.kafka.consumer.group-id}"
	    )
	    
	    public void consume(RentalUpdatedCarEvent event) {
	    	LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
	    	
	    	carService.changeCarState(event.getOldCarId(), event.getNewCarId());
	    	LOGGER.info(event.getOldCarId()+" changed to "+ event.getNewCarId());
	    }
	    
	    public void consume(RentalCreatedEvent event){
	        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

	        carService.changeCarState(event.getCarId());
	        LOGGER.info(event.getCarId()+" state changed");
	    }
	    
	   
}
