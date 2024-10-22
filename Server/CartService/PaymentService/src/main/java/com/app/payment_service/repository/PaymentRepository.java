package com.app.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.payment_service.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    
}
