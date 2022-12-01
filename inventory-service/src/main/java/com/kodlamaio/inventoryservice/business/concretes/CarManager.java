package com.kodlamaio.inventoryservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
import com.kodlamaio.inventoryservice.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
	
	private final CarRepository carRepository;
	private final ModelMapperService mapperService;
	private final ModelService modelService;

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
		carRepository.save(car);
		CreateCarResponse response = mapperService.forResponse().map(car, CreateCarResponse.class);
		return response;
	}

	@Override
	public UpdateCarResponse update(UpdateCarRequest request) {
		checkIfCarExistById(request.getId());
		checkIfCarExistByPlate(request.getPlate());
		modelService.checkIfModelExistById(request.getModelId());
		Car car = mapperService.forRequest().map(request, Car.class);
		carRepository.save(car);
		UpdateCarResponse response = mapperService.forResponse().map(car, UpdateCarResponse.class);
		return response;
	}

	@Override
	public void deleteById(String id) {
		checkIfCarExistById(id);
		carRepository.deleteById(id);	
	}
	
	@Override
	public void changeCarState(String id) {
		checkIfCarExistById(id);
		Car car = carRepository.getCarById(id);
		car.setState(CarState.NOT_AVAILABLE);
		carRepository.save(car);
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
