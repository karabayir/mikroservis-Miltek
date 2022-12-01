package com.kodlamaio.rentalservice.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;

public interface RentalService {

	List<GetAllRentalsResponse> getAll();
	
	CreateRentalResponse add(CreateRentalRequest request);
}
