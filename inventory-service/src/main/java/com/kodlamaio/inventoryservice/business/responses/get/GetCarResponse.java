package com.kodlamaio.inventoryservice.business.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCarResponse {

	private String id;
	private String plate;
	private int modelYear;
	private double dailyPrice;
	private String modelName;
}
