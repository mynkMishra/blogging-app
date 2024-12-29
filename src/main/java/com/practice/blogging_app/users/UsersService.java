package com.practice.blogging_app.users;

import org.springframework.stereotype.Service;

import com.practice.blogging_app.users.dtos.CreateUserRequestDTO;
import com.practice.blogging_app.users.entities.UserEntity;

@Service
public class UsersService {
	
	private final IUsersRepository usersRepository;
	
	public UsersService(IUsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UserEntity createUser(CreateUserRequestDTO request) {
		var newUser = UserEntity.builder()
				.username(request.getUsername())
				.password(request.getPassword())	// TODO: encrypt password
				.email(request.getEmail())
				.build();
		
		return usersRepository.save(newUser);
	}
	
	public UserEntity getUser(String username) {
		return usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
	}
	
	public UserEntity getUser(Long userId) {
		return usersRepository.findById(userId).orElseThrow(() -> new UsersService.UserNotFoundException(userId));
	}
	
	public UserEntity loginUser(String username, String password) {
		var user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		
		// TODO: match password
		return user;
	}
	
	public static class UserNotFoundException extends IllegalArgumentException {
		public UserNotFoundException(String username) {
			super("User " + username + " not found");
		}
		
		public UserNotFoundException(Long authorId) {
			super("User with id: " + authorId + " not found");
		}
	}
}
