package com.kodlamaio.inventoryservice.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.InventoryCreatedEvent;
import com.kodlamaio.common.events.car.CarDeletedEvent;
import com.kodlamaio.common.events.car.CarUpdatedEvent;
import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.kafka.produce.InventoryProducer;
import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.entities.CarState;
import com.kodlamaio.inventoryservice.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	
	private final CarRepository carRepository;
	private final ModelMapperService mapperService;
	private final ModelService modelService;
	private final InventoryProducer inventoryProducer;

	@Override
	public List<GetAllCarsResponse> getAll() {
		List<GetAllCarsResponse> response = carRepository.findAll()
				.stream()
				.map(c-> mapperService.forResponse().map(c, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		return response;
	}
	
	@Override
	public GetCarResponse getById(String id) {
		checkIfCarExistById(id);
		Car car = carRepository.findById(id).orElseThrow();
		return mapperService.forResponse().map(car, GetCarResponse.class);
	}

	@Override
	public CreateCarResponse add(CreateCarRequest request) {
		
		checkIfCarExistByPlate(request.getPlate());
		modelService.checkIfModelExistById(request.getModelId());
		
		Car car = mapperService.forRequest().map(request, Car.class);
		car.setId(UUID.randomUUID().toString());
		Car savedCar =carRepository.save(car);
		
		InventoryCreatedEvent event = mapperService.forResponse().map(savedCar, InventoryCreatedEvent.class);
		inventoryProducer.sendMessage(event);
		
		CreateCarResponse response = mapperService.forResponse().map(car, CreateCarResponse.class);
		return response;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest request) {
		checkIfCarExistById(request.getId());
		checkIfCarExistByPlate(request.getPlate());
		modelService.checkIfModelExistById(request.getModelId());
		Car car = mapperService.forRequest().map(request, Car.class);
		Car savedCar = carRepository.save(car);
		
		
		CarUpdatedEvent event = mapperService.forResponse().map(savedCar, CarUpdatedEvent.class);
		inventoryProducer.sendMessage(event);
		
		UpdateCarResponse response = mapperService.forResponse().map(car, UpdateCarResponse.class);
		return response;
	}

	@Override
	public void deleteById(String id) {
		checkIfCarExistById(id);
		
		CarDeletedEvent event = new CarDeletedEvent();
		event.setCarId(id);
		inventoryProducer.sendMessage(event);
		
		carRepository.deleteById(id);	
	}
	
	@Override
	public void changeCarState(String id) {
		checkIfCarExistById(id);
		Car car = carRepository.getCarById(id);
		car.setState(CarState.NOT_AVAILABLE);
		carRepository.save(car);
	}
	
	@Override
	public void changeCarState(String oldCarId, String newCarId) {
		checkIfCarExistById(oldCarId);
		checkIfCarExistById(newCarId);
		Car oldCar = carRepository.getCarById(oldCarId);
		Car newCar = carRepository.getCarById(newCarId);
		oldCar.setState(CarState.AVAILABLE);
		newCar.setState(CarState.NOT_AVAILABLE);
		carRepository.save(oldCar);
		carRepository.save(newCar);
	}
	
	@Override
	public void checkIfCarAvailable(String id) {
		checkIfCarExistById(id);
		Car car = carRepository.getCarById(id);
		if(car.getState().equals(CarState.NOT_AVAILABLE))
			throw new BusinessException("Car Not Available");
	}
	
	private void checkIfCarExistById(String id) {
		if(carRepository.getCarById(id) == null)
			throw new BusinessException(id+" Car Not Exist");
	}
	
	private void checkIfCarExistByPlate(String name) {
		if(carRepository.existsCarByPlate(name))
			throw new BusinessException("Car Exist");
	}

	

	

	
	
}
