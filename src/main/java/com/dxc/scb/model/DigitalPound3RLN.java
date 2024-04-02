package com.dxc.scb.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Getter
@Setter
public class DigitalPound3RLN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;
    private boolean locked;
 
    
    // Constructors, getters, and setters
    public DigitalPound3RLN() {}

    public DigitalPound3RLN(double balance, boolean locked) {
        this.balance = balance;
        this.locked = locked;
    }

   
}
