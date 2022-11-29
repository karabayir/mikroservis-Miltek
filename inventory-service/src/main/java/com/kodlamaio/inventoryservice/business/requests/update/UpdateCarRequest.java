package com.kodlamaio.inventoryservice.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kodlamaio.inventoryservice.entities.CarState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
	
	@NotBlank
	@NotNull
	private String id;

	@NotBlank
	@NotNull
	private String plate;
	
	
	@Min(value = 2015)
	private int modelYear;
	
	@Min(value = 0)
	private double dailyPrice;
	
	@NotNull
	private CarState state;
	
	@NotBlank
	@NotNull
	private String modelId;
}
