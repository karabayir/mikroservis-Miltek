package com.kodlamaio.rentalservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;	
import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.kafka.RentalProducer;
import com.kodlamaio.rentalservice.repository.RentalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	
	private final RentalRepository rentalRepository;
	private final ModelMapperService mapperService;
	private final RentalProducer  producer;

	@Override
	public List<GetAllRentalsResponse> getAll() {
		List<GetAllRentalsResponse> response = rentalRepository.findAll()
				.stream()
				.map(r-> mapperService.forResponse().map(r, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest request) {
		Rental rental = mapperService.forRequest().map(request, Rental.class);
		rentalRepository.save(rental);
		
		
		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
        rentalCreatedEvent.setCarId(rental.getCarId());
        rentalCreatedEvent.setMessage("Rental Created");

       producer.sendMessage(rentalCreatedEvent);
		 
		return mapperService.forResponse().map(rental, CreateRentalResponse.class);
	}

}
