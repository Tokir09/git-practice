package com.dxc.scb.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dxc.scb.model.User;

@Service
public interface JwtService {

	String generateToken(UserDetails userDetails);
	
	String extractUserName(String token);
	
	boolean isTokenValid(String token, UserDetails userDetails);
	
	String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);
}
