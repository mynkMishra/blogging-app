package com.practice.blogging_app.users.dtos;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.NONE)
public class CreateUserRequestDTO {
	
	@NonNull
	private String username;
	
	@NonNull
	private String password;
	
	@NonNull
	private String email;
}
