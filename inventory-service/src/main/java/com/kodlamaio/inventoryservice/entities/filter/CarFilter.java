package com.kodlamaio.inventoryservice.entities.filter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.kodlamaio.inventoryservice.entities.CarState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarFilter {

	@Id
	private String id;
	
	private String carId;
	private String carPlate;
	private int carModelYear;
	private double carDailyPrice;
	
	@Enumerated(EnumType.STRING)
	private CarState carState;
	
	private String carModelId;
	private String carModelName;
	
	private String carBrandId;
	private String carBrandName;
}
