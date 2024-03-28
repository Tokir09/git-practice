package com.dxc.scb.Service;

import org.springframework.stereotype.Service;

import com.dxc.scb.dto.JwtAuthenticationResponse;
import com.dxc.scb.dto.RefreshTokenRequest;
import com.dxc.scb.dto.SignInRequest;
import com.dxc.scb.dto.SignUpRequest;
import com.dxc.scb.model.User;

@Service
public interface AuthenticationService {

	JwtAuthenticationResponse signin(SignInRequest singinRequest);
	User signup(SignUpRequest signUpReqest) throws Exception;
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
