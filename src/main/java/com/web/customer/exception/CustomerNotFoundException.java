package com.web.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(final String msg) {
        super(msg);
    }
}
