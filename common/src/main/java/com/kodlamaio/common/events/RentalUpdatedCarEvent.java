package com.kodlamaio.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalUpdatedCarEvent {

	private String oldCarId;
	private String newCarId;
}
