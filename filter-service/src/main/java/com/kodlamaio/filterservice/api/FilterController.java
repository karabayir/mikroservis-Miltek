package com.kodlamaio.filterservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.entities.Filter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/filters/")
@AllArgsConstructor
public class FilterController {

	private final FilterService filterService;
	
	@GetMapping("getAll")
	public List<GetAllFiltersResponse> getAll(){
		return filterService.getAll();
	}
	
	@GetMapping("getByBrandName")
	public List<GetAllFiltersResponse> getByBrandName(@RequestParam String brandName){
		return filterService.getByBrandName(brandName);
	}
	
	@GetMapping("getByModelName")
	 public List<GetAllFiltersResponse> getByModelName(@RequestParam String modelName){
		return filterService.getByModelName(modelName);
	 }
	
	@GetMapping("getByPlate")
	 public List<GetAllFiltersResponse> getByPlate(@RequestParam String plate){
	    return filterService.getByPlate(plate);
	 }
	
	@GetMapping("searchByPlate")
	public List<GetAllFiltersResponse> searchByPlate(@RequestParam String plate){
        return filterService.searchByPlate(plate);
	}
	
	@GetMapping("searchByBrandName")
	public List<GetAllFiltersResponse> searchByBrandName(@RequestParam String brandName){
	    return filterService.searchByBrandName(brandName);
	}
	
	@GetMapping("searchByModelName")
	public List<GetAllFiltersResponse> searchByModelName(@RequestParam String modelName){
        return filterService.searchByModelName(modelName);
	}
	
	@GetMapping("getByModelYear/{id}")
	public List<GetAllFiltersResponse> getByModelYear(@PathVariable int modelYear){
		return filterService.getByModelYear(modelYear);
	}
	
	@GetMapping("getByState")
	public List<GetAllFiltersResponse> getByState(@RequestParam String state){
		return filterService.getByState(state);
	}
	
	@GetMapping("getByCarId/{id}")
	public Filter getByCarId(@PathVariable String id) {
		return filterService.getByCarId(id);
	}
	
	@GetMapping("getByModelId/{id}")
	public List<Filter> getByModelId(@PathVariable String modelId){
		return filterService.getByModelId(modelId);
	}
	
	@GetMapping("getByBrandId/{id}")
	public List<Filter> getByBrandId(@PathVariable String brandId){
		return filterService.getByBrandId(brandId);
	}
	
	@PostMapping("add")
	void save(@RequestBody Filter filter) {
		 filterService.save(filter);
	}
	
	@DeleteMapping("deleteById/{id}")
	void deleteById(@PathVariable String id) {
		filterService.deleteById(id);
	}
	
	@DeleteMapping("deleteAllByBrandId/{id}")
	void deleteAllByBrandId(@PathVariable String brandId) {
		filterService.deleteAllByBrandId(brandId);
	}
	
	@DeleteMapping("deleteAllByModelId/{id}")
	void deleteAllByModelId(@PathVariable String modelId) {
		filterService.deleteAllByModelId(modelId);
	}
}
