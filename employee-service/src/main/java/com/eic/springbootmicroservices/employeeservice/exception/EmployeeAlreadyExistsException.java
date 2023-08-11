package com.eic.springbootmicroservices.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistsException extends RuntimeException {
	
	private String message;

	public EmployeeAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}
	
}
