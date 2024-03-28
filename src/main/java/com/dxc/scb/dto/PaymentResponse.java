package com.dxc.scb.dto;

import com.dxc.scb.model.Enums;

import lombok.Data;

@Data
public class PaymentResponse {
    private String transactionId;
    private Enums.PaymentStatus paymentStatus;
    private String message;
 
    // Constructors, getters, and setters
}