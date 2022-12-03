package com.kodlamaio.rentalservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedCarEvent;
import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;	
import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.request.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.response.GetRentalResponse;
import com.kodlamaio.rentalservice.business.response.UpdateRentalResponse;
import com.kodlamaio.rentalservice.client.CarServiceClient;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.kafka.RentalCreateProducer;
import com.kodlamaio.rentalservice.kafka.RentalUpdateProducer;
import com.kodlamaio.rentalservice.repository.RentalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
	
	private final RentalRepository rentalRepository;
	private final ModelMapperService mapperService;
	private final RentalCreateProducer  rentalCreateProducer;
	private final RentalUpdateProducer rentalUpdateProducer;
	private final CarServiceClient carServiceClient;

	@Override
	public List<GetAllRentalsResponse> getAll() {
		List<GetAllRentalsResponse> response = rentalRepository.findAll()
				.stream()
				.map(r-> mapperService.forResponse().map(r, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public GetRentalResponse getById(String id) {
		checkIfRentalExists(id);
		Rental rental = rentalRepository.findById(id).get();
		return mapperService.forResponse().map(rental, GetRentalResponse.class);
	}

	@Override
	public CreateRentalResponse add(CreateRentalRequest request) {
		checkIfExistsRentalByCarId(request.getCarId());
		carServiceClient.checkIfCarAvailable(request.getCarId());
		Rental rental = mapperService.forRequest().map(request, Rental.class);
		rentalRepository.save(rental);
			
		RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
        rentalCreatedEvent.setCarId(rental.getCarId());
        rentalCreatedEvent.setMessage("Rental Created");
  
       rentalCreateProducer.sendMessage(rentalCreatedEvent);
		 
		return mapperService.forResponse().map(rental, CreateRentalResponse.class);
	}

	@Override
	public UpdateRentalResponse update(UpdateRentalRequest request) {
		checkIfRentalExists(request.getId());
		Rental rentalOld= rentalRepository.getRentalById(request.getId());
		Rental rental = mapperService.forRequest().map(request, Rental.class);
		rentalRepository.save(rental);	
		
		RentalUpdatedCarEvent rentalUpdatedCarEvent = new RentalUpdatedCarEvent();
		rentalUpdatedCarEvent.setOldCarId(rentalOld.getCarId());
		rentalUpdatedCarEvent.setNewCarId(rental.getCarId());
		
		rentalUpdateProducer.sendMessage(rentalUpdatedCarEvent);
		
		return mapperService.forResponse().map(rental, UpdateRentalResponse.class);
	}

	@Override
	public void deleteById(String id) {
		checkIfRentalExists(id);
		rentalRepository.deleteById(id);
	}
	
	private void checkIfRentalExists(String id) {
		if(rentalRepository.getRentalById(id) == null)
			throw new BusinessException(id+" Rental not exist");
	}
	
	private void checkIfExistsRentalByCarId(String carId) {
		if(rentalRepository.existsRentalByCarId(carId))
			throw new BusinessException(carId+" Car already rented");
	}

}
