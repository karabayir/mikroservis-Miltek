package com.kodlamaio.inventoryservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventoryservice.entities.Brand;
import com.kodlamaio.inventoryservice.repository.BrandRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{

	private final BrandRepository brandRepository;
	private final ModelMapperService mapperService;
	
	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<GetAllBrandsResponse> response = brandRepository.findAll()
				.stream()
				.map(b-> mapperService.forResponse().map(b,GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		return response;
	}
	
	@Override
	public GetBrandResponse getById(String id) {
		checkIfBrandExistById(id);
		Brand brand = brandRepository.findById(id).orElseThrow();
		return mapperService.forResponse().map(brand, GetBrandResponse.class);
	}

	@Override
	public CreateBrandResponse add(CreateBrandRequest request) {
		checkIfBrandExistByName(request.getName());
		Brand brand = mapperService.forRequest().map(request, Brand.class);
		brandRepository.save(brand);
		CreateBrandResponse response = mapperService.forResponse().map(brand, CreateBrandResponse.class);
		return response;
	}

	@Override
	public UpdateBrandResponse update(UpdateBrandRequest request) {
		checkIfBrandExistById(request.getId());
		checkIfBrandExistByName(request.getName());
		Brand brand = mapperService.forRequest().map(request, Brand.class);
		brandRepository.save(brand);
		UpdateBrandResponse response = mapperService.forResponse().map(brand, UpdateBrandResponse.class);
		return response;
	}
	
	@Override
	public void deleteById(String id) {
		checkIfBrandExistById(id);
		brandRepository.deleteById(id);
	}
	
	@Override
	public void checkIfBrandExistById(String id) {
		if(brandRepository.getBrandById(id) == null)
			throw new BusinessException(id+ " Id Not Exist");
	}
	
	private void checkIfBrandExistByName(String name) {
		Brand brand = brandRepository.findByName(name);
		if(brand != null)
			throw new BusinessException("Brand Exist");
	}



	

}
