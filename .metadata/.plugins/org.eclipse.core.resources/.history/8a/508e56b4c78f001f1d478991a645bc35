package com.profileservice.app.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;


import org.hibernate.validator.constraints.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserProfileTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserProfile() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "http://example.com/image.jpg", 
            "john.doe@example.com", 1234567890L, "About John", new Date(95, 1, 1), 
            "Male", "Admin", "password123", List.of());

        assertTrue(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "http://example.com/image.jpg", 
            "invalid-email", 1234567890L, "About John", new Date(95, 1, 1), 
            "Male", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testInvalidMobileNumber() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "http://example.com/image.jpg", 
            "john.doe@example.com", 123L, "About John", new Date(95, 1, 1), 
            "Male", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testInvalidURL() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "invalid-url", 
            "john.doe@example.com", 1234567890L, "About John", new Date(95, 1, 1), 
            "Male", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testPastDateOfBirth() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "http://example.com/image.jpg", 
            "john.doe@example.com", 1234567890L, "About John", new Date(System.currentTimeMillis() + 86400000), 
            "Male", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testBlankFullName() {
        UserProfile userProfile = new UserProfile(1, "", "http://example.com/image.jpg", 
            "john.doe@example.com", 1234567890L, "About John", new Date(95, 1, 1), 
            "Male", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }

    @Test
    public void testBlankGender() {
        UserProfile userProfile = new UserProfile(1, "John Doe", "http://example.com/image.jpg", 
            "john.doe@example.com", 1234567890L, "About John", new Date(95, 1, 1), 
            "", "Admin", "password123", List.of());

        assertFalse(validator.validate(userProfile).isEmpty());
    }
}
