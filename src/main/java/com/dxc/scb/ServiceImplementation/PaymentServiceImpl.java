package com.dxc.scb.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.scb.Service.BankAccountService;
import com.dxc.scb.Service.DigitalPound3RLNService;
import com.dxc.scb.Service.PaymentService;
import com.dxc.scb.Service.WalletService;
import com.dxc.scb.model.User;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final BankAccountService bankAccountService;
    private final WalletService walletService;
    private final DigitalPound3RLNService digitalPoundRLNService;
    @Autowired
    private User user1;

    public PaymentServiceImpl(BankAccountService bankAccountService, WalletService walletService,
                              DigitalPound3RLNService digitalPound3RLNService,User user1) {
        this.bankAccountService = bankAccountService;
        this.walletService = walletService;
        this.digitalPoundRLNService = digitalPound3RLNService;
    this.user1=user1;
    }
    
   
    @Override
    public String balanceCheck() {
        // Implementation for balance check
        double bankBalance = bankAccountService.getBalance();
        double walletBalance = walletService.getBalance();
        //Account NUmber to get balance or wallet id
        //**************************************************************************************
        double digitalPoundBalance = digitalPoundRLNService.getBalance(0);

        return "Bank Account Balance: " + bankBalance + ", Wallet Balance: " + walletBalance +
               ", Digital Pound Balance: " + digitalPoundBalance;
    }

    @Override
    public String requestToLock() {
        // Implementation for request to lock
        boolean lockResult = digitalPoundRLNService.lock(user1.getId());
        return lockResult ? "Lock requested and granted" : "Lock request failed";
    }

    @Override
    public String processPayment() {
        // Implementation for payment processing
        boolean paymentResult = bankAccountService.transferFunds(walletService.getAccountNumber(), 100.0);
        return paymentResult ? "Payment processed successfully" : "Payment processing failed";
    }

    @Override
    public String lockCancel() {
//    	*********************************************
        // Implementation for lock cancellation  user id associated with lock id
        boolean unlockResult = digitalPoundRLNService.unlock(0);
        return unlockResult ? "Lock cancelled successfully" : "Lock cancellation failed";
    }
    
    public String getBankPaymentDetails() {
        double bankBalance = bankAccountService.getBalance();
        return "Bank Account Balance: " + bankBalance;
    }

    // Get wallet payment details
    public String getWalletPaymentDetails() {
        double walletBalance = walletService.getBalance();
        return "Wallet Balance: " + walletBalance;
    }

    // Get digital pound payment details
    public String getDigitalPoundPaymentDetails() {
        double digitalPoundBalance = digitalPoundRLNService.getBalance(0);
        return "Digital Pound Balance: " + digitalPoundBalance;
    }
    
    
    
    
}





















//public class PaymentServiceImpl implements PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
// 
//    @Override
//    public PaymentResponse processBankPayment(BankPaymentRequest request) {
//		return null;
//        // Implement bank payment processing logic
//    }
// 
//    @Override
//    public PaymentResponse processWalletPayment(WalletPaymentRequest request) {
//		return null;
//        // Implement wallet payment processing logic
//    }
// 
//    @Override
//    public PaymentResponse processDigitalPoundPayment(DigitalPoundPaymentRequest request) {
//		return null;
//        // Implement digital pound payment processing logic
//    }
// 
//    @Override
//    public Payment getPaymentById(Long paymentId) {
//        return paymentRepository.findById(paymentId).orElse(null);
//    }
// 
//    @Override
//    public void cancelPayment(Long paymentId) {
//        // Implement cancellation logic for payment
//    }
//
//	@Override
//	public Double checkBalance(PaymentMode paymentMode) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PaymentLockResponse requestLock(Payment payment, Double amount) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Payment processPayment(Payment payment) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void cancelLock(String lockId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public com.dxc.scb.model.Enums.PaymentMode savePaymentMode(com.dxc.scb.model.Enums.PaymentMode paymentMode) {
//		// TODO Auto-generated method stub
//		return null;
//	}

