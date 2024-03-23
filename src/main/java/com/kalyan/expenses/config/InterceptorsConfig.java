package com.kalyan.expenses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kalyan.expenses.interceptor.RequestInterceptor;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

	@Bean
	RequestInterceptor requestInterceptor() {
		return new RequestInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor());
	}
}
