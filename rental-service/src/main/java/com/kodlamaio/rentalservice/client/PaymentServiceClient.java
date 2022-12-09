package com.kodlamaio.rentalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodlamaio.common.events.request.CreateRentalPaymentRequest;


@FeignClient(value = "paymentserviceclient", url="http://localhost:9010/")
public interface PaymentServiceClient {
	@RequestMapping(method = RequestMethod.POST, value = "payment-service/api/v1/payments/checkPayment")
	void checkBalanceEnough(@RequestBody CreateRentalPaymentRequest request);
}
