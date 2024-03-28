package com.dxc.scb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {

    @NotBlank(message = "House number is required")
    private String houseNo;

    @NotBlank(message = "Apartment name is required")
    private String apartmentName;

    @NotBlank(message = "Street name is required")
    private String streetName;
    
    @NotBlank(message = "City name is required")
    private String city;


    @NotNull(message = "Pin code is required")
    private Integer pinCode;

    
    // Constructors, getters, and setters

    public AddressDto() {
    }

    public AddressDto(String houseNo, String apartmentName, String streetName, Integer pinCode, String city) {
        this.houseNo = houseNo;
        this.apartmentName = apartmentName;
        this.streetName = streetName;
        this.pinCode = pinCode;
        this.city = city;
    }

    
}
