package com.kalyan.expenses.config;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kalyan.expenses.exception.ErrorObject;
import com.kalyan.expenses.exception.ItemExistsException;
import com.kalyan.expenses.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage("ResourceNotFoundException : " + ex.getMessage());
		errorObject.setTimestamp(LocalDate.now());
		ex.printStackTrace();
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage("MethodArgumentTypeMismatchException : " + ex.getMessage());
		errorObject.setTimestamp(LocalDate.now());
		ex.printStackTrace();
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request) {

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObject.setMessage("GeneralException : " + ex.getMessage());
		errorObject.setTimestamp(LocalDate.now());

		ex.printStackTrace();
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorObject> handleUserNameNotFoundException(Exception ex, WebRequest request) {

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		errorObject.setMessage("Username not found : " + ex.getMessage());
		errorObject.setTimestamp(LocalDate.now());

		ex.printStackTrace();
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ItemExistsException.class)
	public ResponseEntity<ErrorObject> handleItemExistsException(ItemExistsException ex, WebRequest request) {

		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.CONFLICT.value());
		errorObject.setMessage("ItemExistsException: " + ex.getMessage());
		errorObject.setTimestamp(LocalDate.now());
		ex.printStackTrace();
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("statusCode", HttpStatus.BAD_REQUEST.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("messages", errors);
		body.put("timestamp", LocalDate.now());
		ex.printStackTrace();
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

	}
}
