package com.kodlamaio.invoiceservice.business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoicesResponse {

	private String id;
	private String rentalId;
	private double totalPrice;
	private String cardHolder;
}
