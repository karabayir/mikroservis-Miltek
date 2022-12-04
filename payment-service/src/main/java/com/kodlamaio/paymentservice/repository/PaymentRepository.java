package com.kodlamaio.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.paymentservice.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
