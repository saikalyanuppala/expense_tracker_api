package com.kalyan.expenses.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthorizationEvents {

	@SuppressWarnings("rawtypes")
	@EventListener
	public void onFailure(AuthorizationDeniedEvent event) {
		log.error("Authorization failed for the user: {} dut to {} ", event.getAuthentication().get().getName(),
				event.getAuthorizationDecision().toString());
	}
}
