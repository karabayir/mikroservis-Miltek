package com.kodlamaio.rentalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(value = "paymentserviceclient", url="http://localhost:9010/")
public interface PaymentServiceClient {
	@RequestMapping(method = RequestMethod.GET, value = "payment-service/api/v1/payments/checkBalanceEnough/{balance}/{totalPrice}")
	@Headers(value = "Content-Type: application/json")
	void checkBalanceEnough(@PathVariable double balance, @PathVariable double totalPrice);
}
