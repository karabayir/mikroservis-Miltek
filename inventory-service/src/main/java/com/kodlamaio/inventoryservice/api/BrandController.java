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

import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateBrandResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/brands/")
@AllArgsConstructor
public class BrandController {

	private final BrandService brandService;
	
	@GetMapping("getAll")
	public List<GetAllBrandsResponse> getAll(){
		return brandService.getAll();
	}
	
	@GetMapping("getById/{id}")
	GetBrandResponse getById(@PathVariable String id) {
		return brandService.getById(id);
	}
	
	@PostMapping("add")
	public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest request) {
		return brandService.add(request);
	}
	
	@PutMapping("update")
	public UpdateBrandResponse update(@Valid @RequestBody UpdateBrandRequest request) {
		return brandService.update(request);
	}
	
	@DeleteMapping("deleteById/{id}")
	public void deleteById(@PathVariable String id) {
		 brandService.deleteById(id);
	}
}
