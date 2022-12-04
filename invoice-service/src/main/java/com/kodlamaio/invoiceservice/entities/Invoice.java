package com.kodlamaio.invoiceservice.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

	private String id;
	private String rentalId;
	private double totalPrice;
	private String cardHolder;
}
