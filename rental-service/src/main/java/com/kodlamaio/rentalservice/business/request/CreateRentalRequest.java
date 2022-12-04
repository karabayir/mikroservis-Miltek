package com.kodlamaio.rentalservice.business.request;

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
public class CreateRentalRequest {

	@NotBlank
	@NotNull
	private String carId;
	
	@Min(value = 1)
	private  int rentForDays;
	
	@Min(value = 0)
	private double dailyPrice;
	
	
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
}
