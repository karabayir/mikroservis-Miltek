package com.kodlamaio.common.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelUpdatedEvent {

	private String id;
	private String name;
	private String brandId;
}
