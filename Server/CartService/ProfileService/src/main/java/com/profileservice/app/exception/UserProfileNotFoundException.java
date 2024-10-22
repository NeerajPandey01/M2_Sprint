package com.profileservice.app.exception;

public class UserProfileNotFoundException extends RuntimeException{

	public UserProfileNotFoundException(String message) {
        super(message);
    }
}
