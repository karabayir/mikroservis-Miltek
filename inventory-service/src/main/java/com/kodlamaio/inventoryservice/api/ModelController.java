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

import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/models/")
@AllArgsConstructor
public class ModelController {

	private final ModelService modelService;
	
	@GetMapping("getAll")
	List<GetAllModelsResponse> getAll(){
		return modelService.getAll();
	}
	
	@GetMapping("getById/{id}")
	GetModelResponse getById(@PathVariable String id) {
		return modelService.getById(id);
	}
	
	@PostMapping("add")
	CreateModelResponse add (@Valid @RequestBody CreateModelRequest request) {
		return modelService.add(request);
	}
	
	@PutMapping("update")
	UpdateModelResponse update(@Valid @RequestBody UpdateModelRequest request) {
		return modelService.update(request);
	}
	
	@DeleteMapping("deleteById/{id}")
	void deleteById(@PathVariable String id) {
		modelService.deleteById(id);
	}
}
