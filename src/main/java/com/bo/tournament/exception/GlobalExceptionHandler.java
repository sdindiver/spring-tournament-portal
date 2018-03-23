package com.bo.tournament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=DataAcessException.class)
	public String handleDataAccessException(){
		
		return "redirect:/error";
	}
	
	
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="forbidden") //404
	public void handleForbiddenException(){
		
//		ErrorData<String> forbiddenError = new ErrorData<String>("FORBIDDEN", "NOT ALLOWED");
//		return new ResponseEntity<>(forbiddenError,HttpStatus.FORBIDDEN);
	}
	
	
	public static class ResourceNotFoundException extends RuntimeException {

		private static final long serialVersionUID = -3332292346834265371L;

		public ResourceNotFoundException(){
			super("EmployeeNotFoundException with");
		}
	}
}
