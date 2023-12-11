package com.projerestapi.projerestapi.exception;

@SuppressWarnings("serial")
public class CountryAlreadyExistsException extends RuntimeException {
	 public CountryAlreadyExistsException(String msg) {
	        super(msg);
	    }

}
