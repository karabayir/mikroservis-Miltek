package com.kodlamaio.inventoryservice.business.abstracts;

import java.util.List;

import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;

public interface CarService {

	List<GetAllCarsResponse> getAll();
	
	GetCarResponse getById(String id);
	
	CreateCarResponse add(CreateCarRequest request);
	
	UpdateCarResponse update(UpdateCarRequest request);
	
	void deleteById(String id);
	
	void changeCarState(String id);
	
	void changeCarState(String oldCarId, String newCarId);
}
