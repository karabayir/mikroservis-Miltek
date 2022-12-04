package com.kodlamaio.paymentservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.paymentservice.business.abstracts.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments/")
@AllArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	
	@GetMapping("checkBalanceEnough/{balance}/{totalPrice}")
	public void checkBalanceEnough(@PathVariable double balance, @PathVariable double totalPrice) {
		paymentService.checkBalanceEnough(balance, totalPrice);
	}
}
