package com.kalyan.expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kalyan.expenses.audit.AuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ExpenseTracketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTracketApiApplication.class, args);
	}

	@Bean
	AuditorAware<Integer> auditorAware() {
		return new AuditorAwareImpl();
	}
}
