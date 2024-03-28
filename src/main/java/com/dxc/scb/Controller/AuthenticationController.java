package com.dxc.scb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.scb.Service.AuthenticationService;
import com.dxc.scb.Service.UserService;
import com.dxc.scb.dto.JwtAuthenticationResponse;
import com.dxc.scb.dto.RefreshTokenRequest;
import com.dxc.scb.dto.SignInRequest;
import com.dxc.scb.dto.SignUpRequest;
import com.dxc.scb.model.User;

@RestController
@RequestMapping("/api/v1/auth")
@Controller
public class AuthenticationController {

    
    
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignUpRequest signUpRequest) throws Exception {
        return authenticationService.signup(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        JwtAuthenticationResponse response = authenticationService.signin(signInRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        JwtAuthenticationResponse response = authenticationService.refreshToken(refreshTokenRequest);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}