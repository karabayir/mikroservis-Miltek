package com.kodlamaio.inventoryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kodlamaio.inventoryservice.entities.filter.CarFilter;

public interface CarFilterRepository extends MongoRepository<CarFilter, String> {

}
