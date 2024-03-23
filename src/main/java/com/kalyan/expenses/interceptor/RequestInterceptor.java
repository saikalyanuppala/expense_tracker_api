package com.kalyan.expenses.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			long currentTime = System.currentTimeMillis();
			request.setAttribute("time", currentTime);
			System.out.println("1 - preHandle() : Before sending request to the Controller");
			System.out.println("Method Type: " + request.getMethod());
			System.out.println("Request URL: " + request.getRequestURI());
		}
		// * If the Exception is caught, this method will return false
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(
				"2 - postHandle() : After the Controller serves the request (before returning back response to the client)");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long currentTime = System.currentTimeMillis();
		long startTime = (long) request.getAttribute("time");
		System.out.println("3 - afterCompletion() : After the request and Response is completed, time taken is "
				+ (currentTime - startTime) + " ms");
	}

}
