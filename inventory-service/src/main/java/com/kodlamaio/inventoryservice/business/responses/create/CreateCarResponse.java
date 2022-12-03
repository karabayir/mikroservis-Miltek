package com.kodlamaio.inventoryservice.business.responses.create;


import com.kodlamaio.inventoryservice.entities.CarState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarResponse {

	private String id;
	private String plate;
	private int modelYear;
	private double dailyPrice;
	private CarState state;
	
}
