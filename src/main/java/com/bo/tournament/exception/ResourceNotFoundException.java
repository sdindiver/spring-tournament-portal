package com.bo.tournament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource Not Found") //404
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -3332292346834265371L;

	public ResourceNotFoundException(int id){
		super("EmployeeNotFoundException with");
	}
}