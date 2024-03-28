package com.dxc.scb.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.dto.BankPaymentRequest;
import com.dxc.scb.dto.DigitalPoundPaymentRequest;
import com.dxc.scb.dto.PaymentResponse;
import com.dxc.scb.dto.WalletPaymentRequest;
import com.dxc.scb.Repository.PaymentRepository;
import com.dxc.scb.model.Payment;
import com.dxc.scb.Service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
 
    @Override
    public PaymentResponse processBankPayment(BankPaymentRequest request) {
		return null;
        // Implement bank payment processing logic
    }
 
    @Override
    public PaymentResponse processWalletPayment(WalletPaymentRequest request) {
		return null;
        // Implement wallet payment processing logic
    }
 
    @Override
    public PaymentResponse processDigitalPoundPayment(DigitalPoundPaymentRequest request) {
		return null;
        // Implement digital pound payment processing logic
    }
 
    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }
 
    @Override
    public void cancelPayment(Long paymentId) {
        // Implement cancellation logic for payment
    }
}
