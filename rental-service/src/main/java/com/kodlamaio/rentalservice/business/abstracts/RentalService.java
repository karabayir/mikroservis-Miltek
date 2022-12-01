package com.kodlamaio.rentalservice.business.abstracts;

import java.util.List;

import com.kodlamaio.rentalservice.business.request.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.request.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.response.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.response.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.response.GetRentalResponse;
import com.kodlamaio.rentalservice.business.response.UpdateRentalResponse;

public interface RentalService {

	List<GetAllRentalsResponse> getAll();
	
	GetRentalResponse getById(String id);
	
	CreateRentalResponse add(CreateRentalRequest request);
	
	UpdateRentalResponse update(UpdateRentalRequest request);
	
	void deleteById(String id);

}
