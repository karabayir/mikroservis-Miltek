package com.kodlamaio.inventoryservice.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarResponse {

	private String id;
	private String plate;
	private int modelYear;
	private double dailyPrice;
	private String modelId;
}
