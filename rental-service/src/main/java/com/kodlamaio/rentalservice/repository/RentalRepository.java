package com.kodlamaio.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentalservice.entities.Rental;

public interface RentalRepository  extends JpaRepository<Rental, String>{

	boolean existsRentalByCarId(String id);
	Rental getRentalById(String id);
}
