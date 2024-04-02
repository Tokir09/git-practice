package com.dxc.scb.dto;

import java.time.LocalDateTime;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseOrderDTO {

    private float amount;
    private int invoiceNumber;
    private String date;
    private String OrderDescription;
    private int orderId;

   
}
