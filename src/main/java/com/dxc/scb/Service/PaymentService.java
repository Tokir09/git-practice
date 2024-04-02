package com.dxc.scb.Service;

import org.springframework.stereotype.Service;
@Service


public interface PaymentService {
    String balanceCheck();
    String requestToLock();
    String processPayment();
    String lockCancel();
	String getWalletPaymentDetails();
	String getDigitalPoundPaymentDetails();
	String getBankPaymentDetails();
    
}
