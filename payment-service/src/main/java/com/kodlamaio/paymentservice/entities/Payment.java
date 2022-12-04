package com.kodlamaio.paymentservice.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	
	@Id
	private String id;
	
	private double totalPrice;
	private String rentalId;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	private String cardNo;
	private String cardHolder;
	private double cardBalance;

}
