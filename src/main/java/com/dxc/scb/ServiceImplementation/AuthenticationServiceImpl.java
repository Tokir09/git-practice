package com.dxc.scb.ServiceImplementation;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dxc.scb.dto.JwtAuthenticationResponse;
import com.dxc.scb.dto.RefreshTokenRequest;
import com.dxc.scb.dto.SignInRequest;
import com.dxc.scb.dto.SignUpRequest;
import com.dxc.scb.Repository.AddressRepository;
import com.dxc.scb.Repository.UserRepository;
import com.dxc.scb.Security.JWTAuthenticationFilter;
import com.dxc.scb.model.Address;
import com.dxc.scb.model.Enums;
import com.dxc.scb.model.User;
import com.dxc.scb.Service.AuthenticationService;
import com.dxc.scb.Service.JwtService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@NoArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	public JWTAuthenticationFilter filter;
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	

	@Autowired
	public AuthenticationManager authenticationManager;
	@Autowired
	public JwtService jwtService;

	@Override
	public User signup(SignUpRequest signUpRequest) throws Exception {
	    // Create and populate User entity
	    User user = new User();
	    user.setEmail(signUpRequest.getEmail());
	    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
	    user.setPhoneNumber(signUpRequest.getPhoneNumber());
	    user.setUsername(signUpRequest.getUsername());
	    if (signUpRequest.getRole().equals("BUYER") ||
	            signUpRequest.getRole().equals("SELLER") ||
	            signUpRequest.getRole().equals("SHIPPER") ||
	            signUpRequest.getRole().equals("ADMIN"))
	        user.setRole(Enums.Role.valueOf(signUpRequest.getRole()));
	    else
	        throw new Exception("Role is undefined");

	    // Create and populate Address entity
	    Address userAddress = new Address();
	    userAddress.setApartment_Name(signUpRequest.getApartmentName());
	    userAddress.setHouse_No(signUpRequest.getHouseNo());
	    userAddress.setCity(signUpRequest.getCity());
	    userAddress.setStreet_Name(signUpRequest.getStreetName());
	    userAddress.setPinCode(signUpRequest.getPinCode());

	    // Set bidirectional relationship
	    user.setAddress(userAddress);
	    userAddress.setUser(user);

	    // Save User entity
	    User savedUser = userRepository.save(user);

	    // Optionally, save Address entity separately if needed
	    // Address savedAddress = addressRepository.save(userAddress);

	    // Return the saved User entity
	    return savedUser;
	}


	public JwtAuthenticationResponse signin(SignInRequest signinRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

//		User user = userRepository.findByEmail(signinRequest.getEmail())
//				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		Optional<User> optionalUser = userRepository.findByEmail(signinRequest.getEmail());

		User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

		// Use the user object here (assuming it's not null)

		String jwt = jwtService.generateToken((UserDetails) user);
		String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), (UserDetails) user);

		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);

		return jwtAuthenticationResponse;
	}

	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();

		if (jwtService.isTokenValid(refreshTokenRequest.getToken(), (UserDetails) user)) {
			String jwt = jwtService.generateToken((UserDetails) user);

			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

			return jwtAuthenticationResponse;

		}

		return null;
	}

}
