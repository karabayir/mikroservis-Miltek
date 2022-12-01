package com.kodlamaio.rentalservice.business.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalResponse {

	private String id;
	private String carId;
	private LocalDateTime dateStarted;
	private  int rentForDays;
	private double dailyPrice;
	private double totalPrice;
}
