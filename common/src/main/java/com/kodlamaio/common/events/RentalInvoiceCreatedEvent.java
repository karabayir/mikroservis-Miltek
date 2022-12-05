package com.kodlamaio.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalInvoiceCreatedEvent {

	private String rentalId;
	private double totalPrice;
	private String cardNo;
	private String cardHolder;
	private double cardBalance;
}
