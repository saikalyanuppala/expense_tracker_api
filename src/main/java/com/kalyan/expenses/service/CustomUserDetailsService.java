package com.kalyan.expenses.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.queryByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("user with email not found " + email));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of());
	}

}
