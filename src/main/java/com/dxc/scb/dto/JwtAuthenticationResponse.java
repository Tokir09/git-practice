package com.dxc.scb.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	public String token;
	
	public String refreshToken;
}
