package com.practice.blogging_app.users;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.blogging_app.users.dtos.CreateUserRequestDTO;
import com.practice.blogging_app.users.dtos.LoginUserRequestDTO;

@Service
public class UsersService {
	
	private final IUsersRepository usersRepository;
	private final ModelMapper modelmapper;
	
	public UsersService(IUsersRepository usersRepository, ModelMapper modelMapper) {
		this.usersRepository = usersRepository;
		this.modelmapper = modelMapper;
	}
	
	public UserEntity createUser(CreateUserRequestDTO request) {
		var newUser = modelmapper.map(request, UserEntity.class);
		// TODO: encrypt password
		
		return usersRepository.save(newUser);
	}
	
	public UserEntity getUser(String username) {
		return usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
	}
	
	public UserEntity getUser(Long userId) {
		return usersRepository.findById(userId).orElseThrow(() -> new UsersService.UserNotFoundException(userId));
	}
	
	public UserEntity loginUser(LoginUserRequestDTO request) {
		var user = usersRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException(request.getEmail()));
		
		// TODO: match password
		return user;
	}
	
	public Iterable<UserEntity> getAllUsers(){
		return usersRepository.findAll();
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
