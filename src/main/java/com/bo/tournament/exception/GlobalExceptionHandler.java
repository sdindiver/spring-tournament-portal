package com.bo.tournament.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=DataAcessException.class)
	public String handleDataAccessException(){
		
		return "redirect:/error";
	}
}
