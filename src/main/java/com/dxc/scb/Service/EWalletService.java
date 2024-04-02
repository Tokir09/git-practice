package com.dxc.scb.Service;

import com.dxc.scb.model.Enums.PaymentStatus;
import com.dxc.scb.model.Payment;

public interface EWalletService {

    Double getBalance(String walletId);

    PaymentStatus processPayment(Payment payment, String lockId);

}

