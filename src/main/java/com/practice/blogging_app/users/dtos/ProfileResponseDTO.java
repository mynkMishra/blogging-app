package com.practice.blogging_app.users.dtos;

import org.springframework.lang.Nullable;

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
public class ProfileResponseDTO {
	
	@NonNull
	private String username;
	@Nullable
	private String bio;
	@Nullable
	private String image;
}
