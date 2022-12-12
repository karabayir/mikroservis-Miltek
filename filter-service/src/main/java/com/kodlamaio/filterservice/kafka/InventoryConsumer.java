package com.kodlamaio.filterservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
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
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryConsumer {
	
	private final FilterService filterService;
	private final ModelMapperService mapperService;
	
	@KafkaListener(topics = "inventory-car-created", groupId = "inventory-create")
	public void consume(InventoryCreatedEvent event) {
		Filter filter = mapperService.forRequest().map(event, Filter.class);
		filterService.save(filter);
	}
	
	@KafkaListener(topics = "inventory-car-updated", groupId = "car-update")
	public void consume(CarUpdatedEvent event) {
		Filter filter = mapperService.forRequest().map(event, Filter.class);
		String id = filterService.getByCarId(event.getCarId()).getId();
		filter.setId(id);
		filterService.save(filter);
	}
	
	@KafkaListener(topics = "inventory-car-deleted", groupId = "car-delete")
	public void consume(CarDeletedEvent event) {
		filterService.deleteById(event.getCarId());
	}
	
	@KafkaListener(topics = "inventory-brand-updated", groupId = "brand-update")
	public void consume(BrandUpdatedEvent event) {
		  filterService.getByBrandId(event.getId())
		  .forEach(filter -> {  filter.setBrandName(event.getName());
	       filterService.save(filter);
	       });
	}
	
	@KafkaListener(topics = "inventory-brand-deleted", groupId = "brand-delete")
	public void consume(BrandDeletedEvent event) {
		filterService.deleteById(event.getBrandId());
	}
	
	@KafkaListener(topics = "inventory-model-updated", groupId = "model-update")
	public void consume(ModelUpdatedEvent event) {
		
		filterService.getByModelId(event.getId())
		    .forEach(filter -> {
            filter.setModelName(event.getName());
            filter.setBrandId(event.getBrandId());
            filter.setBrandName(filterService.getByBrandId(event.getBrandId()).get(0).getBrandName());
            filterService.save(filter);
        });
	}
	
	@KafkaListener(topics = "inventory-model-deleted", groupId = "model-delete")
	public void consume(ModelDeletedEvent event) {
		filterService.deleteById(event.getModelId());
	}
	
	@KafkaListener(topics = "inventory-rental-created", groupId = "rental-create")
	public void consume(RentalCreatedEvent event) {
		Filter filter = filterService.getByCarId(event.getCarId());
		filter.setState("NOT_AVAILABLE");
		filterService.save(filter);
	}
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "rental-update")
	public void consume(RentalUpdatedCarEvent event) {
		Filter oldCar = filterService.getByCarId(event.getOldCarId());
		Filter newCar = filterService.getByCarId(event.getNewCarId());
		oldCar.setState("AVAILABLE");
		newCar.setState("NOT_AVAILABLE");
		filterService.save(oldCar);
		filterService.save(newCar);
	}
	
	@KafkaListener(topics = "inventory-rental-deleted", groupId = "rental-delete")
	public void consume(CarRentalDeletedEvent event) {
		Filter car = filterService.getByCarId(event.getCarId());
		car.setState("AVAILABLE");
		filterService.save(car);
	}
	
}
