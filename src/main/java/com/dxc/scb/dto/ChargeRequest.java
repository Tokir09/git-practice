package com.dxc.scb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeRequest {

    private String token; 
    private double amount;
    private String currency;


}
