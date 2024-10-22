package com.profileservice.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.profileservice.app.service.ProfileService;

import jakarta.validation.Valid;

import com.profileservice.app.entity.UserProfile;
import com.profileservice.app.security.JwtRequest;
import com.profileservice.app.security.JwtResponse;

@RestController
@RequestMapping("/profiles")
public class AuthController {

	@Autowired
	private ProfileService profileService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		return profileService.login(request);
	}

	@PostMapping("/register")
	public ResponseEntity<String> createProfile(@Valid @RequestBody UserProfile profile) {

		return profileService.createProfile(profile);
	}

	@PutMapping("/{profileId}")
	public ResponseEntity<String> updateProfile(@PathVariable("profileId") int profileId, @RequestBody UserProfile profile) {

		return profileService.updateProfile(profileId, profile);

	}

}
