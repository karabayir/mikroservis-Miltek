package com.kodlamaio.rentalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "carserviceclient", url="http://localhost:9010/")
public interface CarServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "inventory-service/api/v1/cars/checkIfCarAvailable/{id}")
	void checkIfCarAvailable(@PathVariable String id);
}
