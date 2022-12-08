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
import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.entities.CarState;
import com.kodlamaio.inventoryservice.kafka.producer.InventoryProducer;
import com.kodlamaio.inventoryservice.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	
	private final CarRepository carRepository;
	private final ModelMapperService mapperService;
	private final ModelService modelService;
	private final InventoryProducer producer;


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
		carRepository.save(car);	
		addToMongo(car.getId());		
		CreateCarResponse response = mapperService.forResponse().map(car, CreateCarResponse.class);		
		return response;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest request) {
		checkIfCarExistById(request.getId());
		modelService.checkIfModelExistById(request.getModelId());
		Car car = mapperService.forRequest().map(request, Car.class);
	    carRepository.save(car);
		updateCarToMongo(request);
		UpdateCarResponse response = mapperService.forResponse().map(car, UpdateCarResponse.class);
		return response;
	}

	@Override
	public void deleteById(String id) {
		checkIfCarExistById(id);
		deleteToMongo(id);
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

	private void addToMongo(String carId) {
		Car savedCar = carRepository.findById(carId).orElseThrow();
		InventoryCreatedEvent event = new InventoryCreatedEvent();
		event.setBrandId(savedCar.getModel().getBrand().getId());
		event.setBrandName(savedCar.getModel().getBrand().getName());
		event.setCarId(savedCar.getId());
		event.setDailyPrice(savedCar.getDailyPrice());
		event.setModelId(savedCar.getModel().getId());
		event.setModelName(savedCar.getModel().getName());
		event.setModelYear(savedCar.getModelYear());
		event.setPlate(savedCar.getPlate());
		event.setState(savedCar.getState().name());
		producer.sendMessage(event);
	}
	

	private void updateCarToMongo(UpdateCarRequest request) {
		Car car = carRepository.findById(request.getId()).orElseThrow();
		car.getModel().setId(request.getModelId());
        car.getModel().getBrand().setId(car.getModel().getBrand().getId());
        car.setState(request.getState());
        car.setPlate(request.getPlate());
        car.setModelYear(request.getModelYear());
        CarUpdatedEvent event = mapperService.forResponse().map(car, CarUpdatedEvent.class);
        event.setState(car.getState().name());
		producer.sendMessage(event);
	}

	private void deleteToMongo(String id) {
		checkIfCarExistById(id);
		CarDeletedEvent event = new CarDeletedEvent();
		event.setCarId(id);
		producer.sendMessage(event);
	}
	
	
}
