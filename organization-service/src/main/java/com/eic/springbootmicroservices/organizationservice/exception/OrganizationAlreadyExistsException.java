package com.eic.springbootmicroservices.organizationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class OrganizationAlreadyExistsException extends RuntimeException{

	private String message;

	public OrganizationAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
