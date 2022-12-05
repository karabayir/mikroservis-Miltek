package com.kodlamaio.filterservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "filter-inventory")
public class Filter {

	@Id
	private String id;
	
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
