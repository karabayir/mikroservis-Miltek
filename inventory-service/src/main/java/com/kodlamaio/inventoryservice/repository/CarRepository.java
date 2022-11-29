package com.kodlamaio.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryservice.entities.Car;

public interface CarRepository extends JpaRepository<Car, String> {

	boolean existsCarByPlate(String name);
	Car getCarById(String id);
}
