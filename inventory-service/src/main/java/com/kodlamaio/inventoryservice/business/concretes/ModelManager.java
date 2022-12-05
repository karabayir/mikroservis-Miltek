package com.kodlamaio.inventoryservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.events.model.ModelDeletedEvent;
import com.kodlamaio.common.events.model.ModelUpdatedEvent;
import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.kafka.produce.InventoryProducer;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryservice.entities.Model;
import com.kodlamaio.inventoryservice.repository.ModelRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
	
	private final ModelRespository modelRespository;
	private final ModelMapperService mapperService;
    private final BrandService brandService;
    private final InventoryProducer inventoryProducer;

	@Override
	public List<GetAllModelsResponse> getAll() {
		List<GetAllModelsResponse> response = modelRespository.findAll()
				.stream()
				.map(m-> mapperService.forResponse().map(m, GetAllModelsResponse.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public GetModelResponse getById(String id) {
		checkIfModelExistById(id);
		Model model = modelRespository.findById(id).orElseThrow();
		return mapperService.forResponse().map(model, GetModelResponse.class);
	}

	@Override
	public CreateModelResponse add(CreateModelRequest request) {
		brandService.checkIfBrandExistById(request.getBrandId());
		Model model = mapperService.forRequest().map(request, Model.class);
		modelRespository.save(model);
		CreateModelResponse response = mapperService.forResponse().map(model, CreateModelResponse.class);
		return response;
	}

	@Override
	public UpdateModelResponse update(UpdateModelRequest request) {
		checkIfModelExistById(request.getId());
		brandService.checkIfBrandExistById(request.getBrandId());
		Model model = mapperService.forRequest().map(request, Model.class);
		Model savedModel = modelRespository.save(model);
		
		ModelUpdatedEvent event = mapperService.forResponse().map(savedModel, ModelUpdatedEvent.class);
		inventoryProducer.sendMessage(event);
		
		UpdateModelResponse response = mapperService.forResponse().map(model, UpdateModelResponse.class);
		return response;
	}

	@Override
	public void deleteById(String id) {
		ModelDeletedEvent event = new ModelDeletedEvent();
		event.setModelId(id);
		inventoryProducer.sendMessage(event);
		modelRespository.deleteById(id);
	}
	
	@Override
	public void checkIfModelExistById(String id) {
		if(modelRespository.getModelById(id) == null)
			throw new BusinessException(id+" Model Not Exist");
	}
	
}
