package com.kodlamaio.inventoryservice.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/cars/")
@AllArgsConstructor
public class CarController {

	private final CarService carService;
	
	@GetMapping("getAll")
	List<GetAllCarsResponse> getAll(){
		return carService.getAll();
	}
	
	@GetMapping("getById/{id}")
	GetCarResponse getById(@PathVariable String id) {
		return carService.getById(id);
	}
	
	@GetMapping("checkIfCarAvailable/{id}")
	void checkIfCarAvailable(@PathVariable String id) {
		carService.checkIfCarAvailable(id);
	}
	
	@PostMapping("add")
	CreateCarResponse add(@Valid @RequestBody CreateCarRequest request) {
		return carService.add(request);
	}
	
	@PutMapping("update")
	UpdateCarResponse update(@Valid @RequestBody UpdateCarRequest request) {
		return carService.update(request);
	}
	
	@DeleteMapping("deleteById/{id}")
	void deleteById(@PathVariable String id) {
		carService.deleteById(id);
	}
}
