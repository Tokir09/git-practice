package com.dxc.scb.dto;

import lombok.Data;

@Data
public class WalletPaymentRequest {
    private String walletId;
    private double amount;
 
    // Constructors, getters, and setters...
}
