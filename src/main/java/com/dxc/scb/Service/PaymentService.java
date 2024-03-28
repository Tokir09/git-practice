package com.dxc.scb.Service;

import org.springframework.stereotype.Service;

import com.dxc.scb.dto.BankPaymentRequest;
import com.dxc.scb.dto.DigitalPoundPaymentRequest;
import com.dxc.scb.dto.PaymentResponse;
import com.dxc.scb.dto.WalletPaymentRequest;
import com.dxc.scb.model.Payment;
@Service
public interface PaymentService {

	// Method to process payment via Bank Account
    PaymentResponse processBankPayment(BankPaymentRequest request);
 
    // Method to process payment via Wallet
    PaymentResponse processWalletPayment(WalletPaymentRequest request);
 
    // Method to process payment via Digital Pound
    PaymentResponse processDigitalPoundPayment(DigitalPoundPaymentRequest request);
 
    // Method to retrieve payment details by ID
    Payment getPaymentById(Long paymentId);
 
    // Method to cancel payment
    void cancelPayment(Long paymentId);
}
