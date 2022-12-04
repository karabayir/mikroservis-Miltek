package com.kodlamaio.paymentservice.business.response;

import com.kodlamaio.paymentservice.entities.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPaymentsResponse {

	private String id;
	private String rentalId;
	private double doublePrice;
	private String cardNo;
	private String cardHolder;
	private double cardBalance;
	private PaymentStatus paymentStatus;
}
