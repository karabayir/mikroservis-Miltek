package com.kodlamaio.paymentservice.business.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

	@NotBlank
	@NotNull
	private String rentalId;
	
	@Min(value = 0)
	private double doublePrice;
	
	@NotBlank
	@NotNull
	@Size(min = 16, max =16)
	private String cardNo;
	
	@NotBlank
	@NotNull
	@Size(min = 4)
	private String cardHolder;
	
	@Min(value = 0)
	private double cardBalance;
	
	@Min(value = 0)
	private double totalPrice;
}
