package com.kalyan.expenses.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.service.UserService;

import lombok.RequiredArgsConstructor;

@Component("auditorAware")
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Integer> {

	private final UserService service;

	@Override
	public Optional<Integer> getCurrentAuditor() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = service.findUserByEmail(email);
		return Optional.of(user.getId());
	}
}