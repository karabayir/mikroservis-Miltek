package com.kodlamaio.inventoryservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.kodlamaio.common.utilities.mapper.ModelMapperManager;
import com.kodlamaio.common.utilities.mapper.ModelMapperService;
import com.kodlamaio.inventoryservice.repository.CarFilterRepository;

@SpringBootApplication
@EnableDiscoveryClient

@EnableMongoRepositories(basePackageClasses = CarFilterRepository.class)
@EnableJpaRepositories(excludeFilters = 
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CarFilterRepository.class))

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ModelMapperService getModelMapperService(ModelMapper mapper) {
		return new ModelMapperManager(mapper);
	}
}