package com.profileservice.app.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.profileservice.app.entity.Cart;
import com.profileservice.app.entity.Order;
import com.profileservice.app.entity.UserProfile;
import com.profileservice.app.security.JwtRequest;
import com.profileservice.app.security.JwtResponse;
public interface ProfileService {
    ResponseEntity<String> createProfile(UserProfile profile);
    ResponseEntity<List<UserProfile>> getAllProfiles();
    ResponseEntity<String> deleteProfile(int profileId);
	ResponseEntity<String> updateProfile(int profileId, UserProfile profile);
	ResponseEntity<UserProfile> getProfileById(int profileId);
	ResponseEntity<List<Cart>> getCart(int customerId);
	ResponseEntity<List<Order>> getOrders(int customerId);
	UserProfile getProfileByEmailId(String emailId);
	public ResponseEntity<JwtResponse> login(JwtRequest request);
}

