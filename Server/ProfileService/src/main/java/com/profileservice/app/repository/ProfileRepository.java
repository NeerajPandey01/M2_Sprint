package com.profileservice.app.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profileservice.app.entity.UserProfile;

public interface ProfileRepository extends JpaRepository<UserProfile, Integer> {

	UserProfile findByEmailId(String emailId);
}
