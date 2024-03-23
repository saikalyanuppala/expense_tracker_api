package com.kalyan.expenses.audit;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {

	@Override
	public Optional<Integer> getCurrentAuditor() {
		return Optional.of(new Random().nextInt(1, 1000));
	}
}