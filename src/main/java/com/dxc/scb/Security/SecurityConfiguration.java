package com.dxc.scb.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.dxc.scb.model.Enums;

import jakarta.annotation.PostConstruct;

import com.dxc.scb.Service.UserService;

import lombok.RequiredArgsConstructor;



@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

	
	@Autowired
	private  JWTAuthenticationFilter jwtAuthenticationfilter;

//    public SecurityConfiguration(JWTAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationfilter = jwtAuthenticationFilter;
//    }
	@Autowired
	private  UserService userService;

	



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).headers(httpSecurityHeadersConfigurer -> {
			httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
		}).authorizeHttpRequests(request -> request
//            	.requestMatchers("/h2-console/**").permitAll()
//            	.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
				.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
				.requestMatchers("/api/v1/auth/**").permitAll().requestMatchers("/api/v1/admin")
				.hasAnyAuthority(Enums.Role.ADMIN.name()).requestMatchers("/api/v1/buyer")
				.hasAnyAuthority(Enums.Role.BUYER.name()).requestMatchers("/api/v1/seller")
				.hasAnyAuthority(Enums.Role.SELLER.name()).requestMatchers("/api/v1/shipper")
				.hasAnyAuthority(Enums.Role.SHIPPER.name()).anyRequest().authenticated())

				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();

	}


	
//	  @Bean
//	    public JWTAuthenticationFilter jwtAuthenticationFilter() {
//	        return new JWTAuthenticationFilter();
//	    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
