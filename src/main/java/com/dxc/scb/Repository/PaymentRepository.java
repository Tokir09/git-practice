package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
