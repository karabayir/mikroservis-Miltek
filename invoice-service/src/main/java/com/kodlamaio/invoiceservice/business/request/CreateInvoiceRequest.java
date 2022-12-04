package com.kodlamaio.invoiceservice.business.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

	private String rentalId;
	private double totalPrice;
	private String cardHolder;
}
