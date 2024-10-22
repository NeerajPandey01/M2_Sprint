package com.profileservice.app.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@NotBlank(message = "House number is required")
	private String houseNumber;

	@NotBlank(message = "Street name is required")
	private String streetName;

	@NotBlank(message = "Colony name is required")
	private String colonyName;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Pincode is required")
	private String pincode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_profile_id")
	@JsonBackReference
	private UserProfile userProfile;

}
