package com.kodlamaio.filterservice.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.entities.Filter;
import com.kodlamaio.filterservice.repository.FilterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
	
	private final FilterRepository filterRepository;
	private final ModelMapperService mapperService;

	@Override
	public List<GetAllFiltersResponse> getAll() {
		return filterRepository.findAll()
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> getByBrandName(String brandName) {
		return filterRepository.findByBrandNameContainingIgnoreCase(brandName)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> getByModelName(String modelName) {
		return filterRepository.findByModelNameContainingIgnoreCase(modelName)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> getByPlate(String plate) {
		return filterRepository.findByPlateIgnoreCase(plate)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> searchByPlate(String plate) {
		return filterRepository.findByPlateContainingIgnoreCase(plate)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> searchByBrandName(String brandName) {
		return filterRepository.findByBrandNameContainingIgnoreCase(brandName)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> searchByModelName(String modelName) {
		return filterRepository.findByModelNameContainingIgnoreCase(modelName)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> getByModelYear(int modelYear) {
		return filterRepository.findByModelYear(modelYear)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<GetAllFiltersResponse> getByState(String state) {
		return filterRepository.findByState(state)
				.stream()
				.map(f-> mapperService.forResponse().map(f, GetAllFiltersResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public Filter getByCarId(String id) {
		return filterRepository.findByCarId(id);
	}

	@Override
	public List<Filter> getByModelId(String modelId) {
		return filterRepository.findByModelId(modelId);
	}

	@Override
	public List<Filter> getByBrandId(String brandId) {
		return filterRepository.findByBrandId(brandId);
	}

	@Override
	public void save(Filter filter) {
		filterRepository.save(filter);
		
	}

	@Override
	public void deleteById(String id) {
		filterRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllByBrandId(String brandId) {
		filterRepository.deleteAllByBrandId(brandId);
		
	}

	@Override
	public void deleteAllByModelId(String modelId) {
		filterRepository.deleteAllByModelId(modelId);
		
	}

	

}
