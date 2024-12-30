package com.practice.blogging_app.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDTO {
	@NonNull
	private String password;
	
	@NonNull
	private String email;
}
