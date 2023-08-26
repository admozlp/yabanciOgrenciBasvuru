package com.ahievran.yabanciOgrenciBasvuru.core.utilities.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailVerificationException extends UsernameNotFoundException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 197823386638077040L;

	public EmailVerificationException(String message) {
        super(message);
    }
}
