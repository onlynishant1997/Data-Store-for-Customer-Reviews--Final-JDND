package com.udacity.course3.reviews.exceptionhandler;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customer Exception Handler for ResponseEntity.
 * 
 * @author Nishant
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handle method argument not valid exception.
	 *
	 * @param ex the exception
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()).toString();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", errorMessage);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
}
