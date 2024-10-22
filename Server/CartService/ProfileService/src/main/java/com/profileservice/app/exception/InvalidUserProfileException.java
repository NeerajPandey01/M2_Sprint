package com.profileservice.app.exception;

public class InvalidUserProfileException extends RuntimeException {
    public InvalidUserProfileException(String message) {
        super(message);
    }
}
