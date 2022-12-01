package com.kodlamaio.rentalservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;

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
	
	@PostMapping("add")
	CreateRentalResponse add(@RequestBody CreateRentalRequest request) {
		return rentalService.add(request);
	}
}
