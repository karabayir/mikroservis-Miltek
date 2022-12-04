package com.kodlamaio.paymentservice.business.concretes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.common.utilities.exception.BusinessException;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.request.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.response.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.response.GetAllPaymentsResponse;
import com.kodlamaio.paymentservice.entities.Payment;
import com.kodlamaio.paymentservice.entities.PaymentStatus;
import com.kodlamaio.paymentservice.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService{

	private final PaymentRepository paymentRepository;
	private final ModelMapperService mapperService;

	@Override
	public List<GetAllPaymentsResponse> getAll() {
		return paymentRepository.findAll()
				.stream()
				.map(p-> mapperService.forResponse().map(p, GetAllPaymentsResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public CreatePaymentResponse add(CreatePaymentRequest request) {
		checkBalanceEnough(request.getCardBalance(), request.getTotalPrice());
		Payment payment=mapperService.forRequest().map(request, Payment.class);
		payment.setId(UUID.randomUUID().toString());
		payment.setPaymentStatus(PaymentStatus.ONAY);
		paymentRepository.save(payment);
		return mapperService.forResponse().map(payment, CreatePaymentResponse.class);
	}
	
	@Override
	public void checkBalanceEnough(double balance, double totalPrice) {
		if(balance<totalPrice) 	
			throw new BusinessException("Yetersiz Bakiye");
	}
}
