package com.kodlamaio.rentalservice.api;

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

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.request.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.response.GetRentalResponse;
import com.kodlamaio.rentalservice.business.response.UpdateRentalResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/rentals/")
@AllArgsConstructor
public class RentalController {

	private final RentalService rentalService;
	
	@GetMapping("getAll")
	List<GetAllRentalsResponse> getAll(){
		return rentalService.getAll();
	}
	
	@GetMapping("getById/{id}")
	GetRentalResponse getById(@PathVariable String id) {
		return rentalService.getById(id);
	}
	
	@PostMapping("add")
	CreateRentalResponse add(@Valid @RequestBody CreateRentalRequest request) {
		return rentalService.add(request);
	}
	
	@PutMapping("update")
	UpdateRentalResponse update(@Valid  @RequestBody UpdateRentalRequest request) {
		return rentalService.update(request);
	}
	
	@DeleteMapping("deleteById/{id}")
	void deleteById(@PathVariable String id) {
		rentalService.deleteById(id);
	}
	
}
