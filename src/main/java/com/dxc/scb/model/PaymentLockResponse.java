package com.dxc.scb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentLockResponse {

    private boolean success;
    private String message;
    private String lockId; // Optional field

    
    public PaymentLockResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
}