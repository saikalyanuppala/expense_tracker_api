package com.kalyan.expenses.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.dto.UserDTO;
import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO dto) {
		return ResponseEntity.status(201).body(userService.saveUser(dto));
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findUserById(id));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.findUserByEmail(email));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO dto, @PathVariable Integer id) {
		return ResponseEntity.ok(userService.updateUser(dto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
		return new ResponseEntity<String>("User deleted with id " + id, HttpStatus.NO_CONTENT);
	}
}
