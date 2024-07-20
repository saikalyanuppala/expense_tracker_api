package com.kalyan.expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ExpenseTracketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTracketApiApplication.class, args);
	}
}
