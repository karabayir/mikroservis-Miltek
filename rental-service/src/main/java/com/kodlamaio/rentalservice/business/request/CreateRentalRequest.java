package com.kodlamaio.rentalservice.business.request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {

	private String carId;
	private  int rentForDays;
	private double dailyPrice;
	private double totalPrice;
}
