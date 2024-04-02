package com.dxc.scb.Security;

import java.io.IOException;
import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dxc.scb.Service.JwtService;
import com.dxc.scb.Service.UserService;
import com.dxc.scb.ServiceImplementation.UserServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@AutoConfiguration

public class JWTAuthenticationFilter extends OncePerRequestFilter {

			private final JwtService jwtService = null;

			private final UserService userService = null;

		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

				throws ServletException, IOException {

			final String authHeader = request.getHeader("Authorization");

			 String jwt=null;

			 String userEmail=null;

			if(authHeader != null || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer " )) {

				filterChain.doFilter(request,response);

				return;

			}

			jwt = authHeader.substring(7);

			userEmail = jwtService.extractUserName(jwt);

			if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
	 
				if(jwtService.isTokenValid(jwt, userDetails)) {

					SecurityContext securityContext  = SecurityContextHolder.createEmptyContext();

					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

					token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.setContext(securityContext);

				}

			}

			filterChain.doFilter(request,response);

		}	
	
	
}
//	@Value("${jwt.secret}")
//	private String secretKey;
////	private  JwtService jwtService;
////
////	private  UserService userService;
////	public JWTAuthenticationFilter() {
////		
////		
////	}
////
////	public JWTAuthenticationFilter(UserService userService) {
////
////	//
////		this.userService = userService;
////	}
////
////	public JWTAuthenticationFilter(JwtService jwtService, UserService userService) {
////		super();
////		this.jwtService = jwtService;
////		this.userService = userService;
////	}
////
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		final String authHeader = request.getHeader("Authorization");
//		String jwt = null;
//		String userEmail = null;
//
//		if (authHeader != null || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//
//		jwt = authHeader.substring(7);
////		userEmail = jwtService.extractUserName(jwt);
//		userEmail = extractUserNameFromJwt(jwt);
//
//		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			// 1042024
//			// UserDetails userDetails =
//			// userService.userDetailsService().loadUserByUsername(userEmail);
//			UserDetails userDetails = loadUserByUsername(userEmail);
////1042024 changes
//			// if (jwtService.isTokenValid(jwt, userDetails)) {
//			if (isTokenValid(jwt, userDetails)) {
//
//				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//
//				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
//						userDetails.getAuthorities());
//
//				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.setContext(securityContext);
//
//			}
//
//		}
//
//		filterChain.doFilter(request, response);
//
//	}
//
////*
//	private String extractUserNameFromJwt(String jwt) {
//		try {
//			Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwt).getBody();
//			return claims.getSubject();
//		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
//			return null; // Token is not valid
//		}
//
//	}
//
//	private UserDetails loadUserByUsername(String userEmail) {
//		// Implement logic to load user details by username (e.g., from database)a
//		return new org.springframework.security.core.userdetails.User("admin@example.com",
//				"$2a$10$MR1tVoJL1hZjA5Zd.Y2Ql.1E5aMPbdDE5l6qYzOqCLH2nEggWjAyG", true, true, true, true, null);
//	}
//
//	private boolean isTokenValid(String jwt, UserDetails userDetails) {
//		try {
//			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwt);
//			return true;
//		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
//			return false; // Token is not valid
//		}
//	}
//
//	private Key getSigningKey() {
//		return Keys.hmacShaKeyFor(secretKey.getBytes());
//	}
//
//}// *
