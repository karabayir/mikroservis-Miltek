package com.kodlamaio.inventoryservice.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	@Id
	private String id;
	
	private String plate;
	private int modelYear;
	private double dailyPrice;
	
	@Enumerated(EnumType.STRING)
	private CarState state;
	
	@ManyToOne()
	@JoinColumn(name = "model_id")
	private Model model;
}
