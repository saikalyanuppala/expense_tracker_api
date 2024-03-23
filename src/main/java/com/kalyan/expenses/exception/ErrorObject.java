package com.kalyan.expenses.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorObject {

	private Integer statusCode;

	private String message;

	private LocalDate timestamp;
}