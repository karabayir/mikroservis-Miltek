package com.kodlamaio.paymentservice.business.abstracts;

import java.util.List;

import com.kodlamaio.common.events.request.CreateRentalPaymentRequest;
import com.kodlamaio.paymentservice.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.response.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.response.GetAllPaymentsResponse;

public interface PaymentService {

	List<GetAllPaymentsResponse> getAll();
	
	CreatePaymentResponse add(CreatePaymentRequest request);
	
	void checkIfPaymentSuccess(CreateRentalPaymentRequest request);
}
