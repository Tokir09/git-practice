package com.dxc.scb.model;

public class Enums {
	 
    public enum PaymentMode {
        CREDIT_CARD,
        DEBIT_CARD,
        NET_BANKING,
        UPI,
        CASH_ON_DELIVERY
    }

    public enum PaymentStatus {
        FUNDS_LOCKED,
        FUNDS_UNLOCKED,
        PAYMENT_RECEIVED,
        PAYMENT_FAILED
    }

    public enum OrderStatus {
        ORDER_PLACED,
        SHIPPED,
        DELIVERED,
        RETURNED,
        CANCELLED
    }
    
    public enum Role{
    	BUYER,
    	SELLER,
    	SHIPPER,
    	ADMIN
    }

}
