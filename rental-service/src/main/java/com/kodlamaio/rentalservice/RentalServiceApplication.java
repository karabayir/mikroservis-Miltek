package com.kodlamaio.rentalservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.utilities.mapper.ModelMapperManager;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;

@SpringBootApplication
@EnableDiscoveryClient
public class RentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ModelMapperService getModelMapperService(ModelMapper mapper) {
		return new ModelMapperManager(mapper);
	}
	
	@Bean
	public RentalCreatedEvent getRentalCreatedEvent() {
		return new RentalCreatedEvent();
	}
}