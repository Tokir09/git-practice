package com.dxc.scb.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpRequest {

	private String username;
	
	private String email;
	private String password;
	private String role;
	private String phoneNumber;
	
	//Address details
	private String houseNo;
	private String apartmentName;
	private String streetName;
	private String city;
	private Integer pinCode;
	
}
