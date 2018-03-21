package com.bo.tournament.exception;

public class ErrorData<T> {

	private T code;
	private T message;
	
	public ErrorData(T code, T message){
		this.code=code;
		this.message=message;
	}

	public T getCode() {
		return code;
	}

	public T getMessage() {
		return message;
	}
	
	
}


