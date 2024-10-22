package com.profileservice.app.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;

}