package com.sanchezjm.tuto.feign.exception;

public class BusinessException extends RuntimeException{

	public BusinessException(String reasonPhrase) {
		super(reasonPhrase);
	}

}
