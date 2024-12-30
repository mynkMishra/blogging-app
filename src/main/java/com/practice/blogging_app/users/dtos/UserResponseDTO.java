package com.practice.blogging_app.users.dtos;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
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
public class UserResponseDTO {
	@NonNull
	private Long id;
	
	@NonNull
	private String username;
	
	@NonNull
	private String email;
	
	@Nullable
	private String bio;
	
	@Nullable
	private String image;
}
