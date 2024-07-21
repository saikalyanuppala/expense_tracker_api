package com.kalyan.expenses.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.dto.JwtResponse;
import com.kalyan.expenses.dto.LoginDTO;
import com.kalyan.expenses.dto.UserDTO;
import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.service.UserService;
import com.kalyan.expenses.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	private final AuthenticationManager authManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsService detailsService;

	@PostMapping("/login1")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginDTO dto) throws Exception {
		authenticate(dto.getEmail(), dto.getPassword());
		final UserDetails userDetails = detailsService.loadUserByUsername(dto.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.ACCEPTED);
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody UserDTO dto) {
		return new ResponseEntity<User>(userService.saveUser(dto), HttpStatus.CREATED);
	}

}
