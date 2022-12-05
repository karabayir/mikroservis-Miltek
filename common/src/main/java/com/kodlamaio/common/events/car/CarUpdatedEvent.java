package com.kodlamaio.common.events.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdatedEvent {

	private String carId;
	private String plate;
	private int modelYear;
	private double dailyPrice;
	private String state;
	
	private String modelId;
	private String modelName;
	
	private String brandId;
	private String brandName;
}
