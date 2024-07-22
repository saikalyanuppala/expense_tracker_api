package com.kalyan.expenses.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.dto.LoginDTO;
import com.kalyan.expenses.dto.UserDTO;
import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
	private final AuthenticationManager authManager;

	@PostMapping("/login1")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return ResponseEntity.ok("logged in user success");
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody UserDTO dto) {
		return new ResponseEntity<User>(userService.saveUser(dto), HttpStatus.CREATED);
	}

}
