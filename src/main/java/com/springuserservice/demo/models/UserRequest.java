package com.springuserservice.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest (@NotBlank(message = "name should not be blank") String name,
		
		@NotBlank @Email String email) {
	

}
