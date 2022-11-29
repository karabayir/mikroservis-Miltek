package com.kodlamaio.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryservice.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

	Brand findByName(String name);
	Brand getBrandById(String id);
}
