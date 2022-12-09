package com.kodlamaio.paymentservice.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.common.events.request.CreateRentalPaymentRequest;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments/")
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	
	@PostMapping("checkPayment")
	void checkIfPaymentSuccess(@RequestBody CreateRentalPaymentRequest request) {
		paymentService.checkIfPaymentSuccess(request);
	}
}
