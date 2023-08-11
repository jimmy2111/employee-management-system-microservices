package com.eic.springbootmicroservices.apigateway.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false), "INTERNAL SERVER ERROR");

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(UserAPIException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(UserAPIException exception,
																		 WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"BAD REQUEST");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
