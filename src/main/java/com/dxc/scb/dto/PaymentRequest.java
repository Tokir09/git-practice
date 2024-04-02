package com.dxc.scb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class PaymentRequest {

    private Long orderId;
    private Long paymentModeId; // Consider using PaymentMode object
    private Double amount;
	public PaymentRequest(Long orderId, Long paymentModeId, Double amount) {
		super();
		this.orderId = orderId;
		this.paymentModeId = paymentModeId;
		this.amount = amount;
	}

   
}
