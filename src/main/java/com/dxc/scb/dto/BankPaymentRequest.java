package com.dxc.scb.dto;

import lombok.Data;

@Data
public class BankPaymentRequest {
    private String accountNumber;
    private double amount;
 
    // Constructors, getters, and setters...
}