package com.dxc.scb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.scb.dto.PaymentMode;
import com.dxc.scb.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

		void savePayment(Payment payment);
	    PaymentMode savePaymentMode(PaymentMode payment);

	    Payment updatePayment(Payment payment);

	    Payment findPayment(Long long1);

	   

}
