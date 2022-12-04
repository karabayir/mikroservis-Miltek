package com.kodlamaio.paymentservice.business.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

	private String rentalId;
	private double doublePrice;
	private String cardNo;
	private String cardHolder;
	private double cardBalance;
	private double totalPrice;
}
