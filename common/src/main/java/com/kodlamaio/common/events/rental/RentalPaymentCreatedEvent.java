package com.kodlamaio.common.events.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalPaymentCreatedEvent {

	private String rentalId;
	private double totalPrice;
	private String cardNo;
	private String cardHolder;
	private double cardBalance;
}
