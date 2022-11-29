package com.kodlamaio.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.inventoryservice.entities.Model;

public interface ModelRespository extends JpaRepository<Model, String> {

	Model getModelById(String id);
}
