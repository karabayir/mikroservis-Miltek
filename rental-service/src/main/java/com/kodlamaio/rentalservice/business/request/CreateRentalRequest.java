package com.kodlamaio.rentalservice.business.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {

	@NotBlank
	@NotNull
	private String carId;
	
	@Min(value = 1)
	private  int rentForDays;
	
	@Min(value = 0)
	private double dailyPrice;
	
	@Min(value = 0)
	private double totalPrice;
}
